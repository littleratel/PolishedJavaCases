package connectionpool.eric;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import connectionpool.eric.exception.AcquireObjectFailedException;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@ToString
@Slf4j
public class LightweightConnectionPoolImpl<T> implements LightweightConnectionPool<T> {

    private static final long POLL_TIMEOUT_MILLIS = 10;

    private final int capacity;
    private final BlockingQueue<PoolObject<T>> pool;
    private final ObjectFactory<T> objectFactory;
    private final Set<UsedObject<T>> usedObjects = new HashSet<>();
    private final Semaphore available;

    private final Object lock = new Object();

    // A consumer that shall be executed on the pooled object when object is being invalidated
    private Consumer<T> defaultInvalidateConsumer;

    public LightweightConnectionPoolImpl(int capacity, ObjectFactory<T> objectFactory, Consumer<T> invalidateConsumer) {
        this.capacity = capacity;
        this.objectFactory = objectFactory;
        this.defaultInvalidateConsumer = invalidateConsumer;
        pool = new ArrayBlockingQueue<>(capacity, true);
        available = new Semaphore(capacity);
    }

    @Override
    public T take() throws AcquireObjectFailedException {
        return take(Thread.currentThread());
    }

    private T take(Thread thread) {
        UsedObject<T> usedObject = getUsedObject(thread);
        if (usedObject != null) {
            return usedObject.getPoolObject().getObject();
        }

        try {
            if (!available.tryAcquire(0, TimeUnit.SECONDS)) {
                available.acquire(); // blocked
            }
            PoolObject<T> poolObject = pool.poll(POLL_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
            if (poolObject != null) {
                log.trace("Successfully reserved a previously created pooled instance");
                registerObjectAsUsed(poolObject, thread);
                return poolObject.getObject();
            } else {
                log.trace("Creating new pooled instance...");
                poolObject = createObject();
                long createdObjects = capacity - available.availablePermits();
                log.trace("Created new pooled instance {} #created={}, max capacity={}", poolObject, createdObjects, capacity);
                registerObjectAsUsed(poolObject, thread);
                return poolObject.getObject();
            }
        } catch (InterruptedException e) {
            available.release();
            Thread.interrupted();
            throw new AcquireObjectFailedException(e);
        } catch (RuntimeException e) {
            available.release();
            throw new AcquireObjectFailedException("Failed to create new pool instance", e);
        }
    }

    private UsedObject getUsedObject(Thread thread) {
        synchronized (lock) {
            for (UsedObject<T> usedObject : usedObjects) {
                if (thread.equals(usedObject.getThread())) {
                    return usedObject;
                }
            }
        }
        return null;
    }

    private void registerObjectAsUsed(PoolObject<T> object, Thread thread) {
        synchronized (lock) {
            usedObjects.add(new UsedObject<>(object, thread));
        }
    }

    private PoolObject<T> createObject() {
        T object = objectFactory.create();
        String id = String.valueOf(System.nanoTime());
        PoolObject<T> poolObject = new PoolObject<>(id, object);
        return poolObject;
    }

    @Override
    public void put(T object) {
        List<UsedObject> usedObjectsCopy = getUsedObjectsCopy();
        for (UsedObject usedObject : usedObjectsCopy) {
            PoolObject poolObject = usedObject.getPoolObject();
            if (poolObject.getObject().equals(object)) {
                releaseUsedObject(usedObject);
                return;
            }
        }
    }

    private List<UsedObject> getUsedObjectsCopy() {
        synchronized (lock) {
            return Lists.newArrayList(usedObjects);
        }
    }

    private void releaseUsedObject(UsedObject<T> usedObject) {
        try {
            synchronized (lock) {
                usedObjects.remove(usedObject);
            }
            PoolObject<T> poolObject = usedObject.getPoolObject();
            long reservedTime = System.currentTimeMillis() - usedObject.getReservationTimestampInMilliSeconds();
            if (usedObject.isValid()) {
                // Object is OK to be reused - put back in pool.
                log.trace("Putting back pooled object. reservedTime: {} milliSec, pool remainingCapacity before put: {}", reservedTime
                        , pool.remainingCapacity());
                pool.add(poolObject);
            } else {
                // Don't add to pool but throw away and allow pool to create a new instance instead...
                log.trace("Destroying a pooled object (that was reserved: {} milliSec) and releasing available capacity...", reservedTime);
            }
        } finally {
            available.release();
        }
    }

    @Override
    public void releaseForThread(Thread thread) {
        UsedObject<T> usedObject = getUsedObject(thread);
        if (usedObject != null) {
            T object = usedObject.getPoolObject().getObject();
            put(object);
        } else {
            log.trace("Nothing to release");
        }
    }

    @Override
    public Optional<T> getTaken() {
        return getTaken(Thread.currentThread());
    }

    @Override
    public Optional<T> getTaken(Thread thread) {
        UsedObject<T> usedObject = getUsedObject(thread);
        return usedObject != null ? Optional.of(usedObject.getPoolObject().getObject()) : Optional.empty();
    }

    @Override
    public void invalidate(Thread thread) {
        invalidate(thread, defaultInvalidateConsumer);
    }

    @Override
    public void invalidate(Thread thread, Consumer<T> consumer) {
        UsedObject<T> usedObject = getUsedObject(thread);
        if (usedObject != null) {
            usedObject.invalidate();
            PoolObject<T> poolObject = usedObject.getPoolObject();
            invalidateConsumer(consumer, poolObject);
        }
    }

    private void invalidateConsumer(Consumer<T> consumer, PoolObject<T> poolObject) {
        if (consumer != null) {
            try {
                consumer.accept(poolObject.getObject());
            } catch (RuntimeException e) {
                log.debug("Invalidation consumer failed: {} in pool: ", poolObject.getId(), this, e);
            }
        }
    }

    @Override
    public void clear() {
        synchronized (lock) {
            for (UsedObject<T> usedObject : usedObjects) {
                releaseUsedObject(usedObject);
            }
        }
        if (defaultInvalidateConsumer != null) {
            Collection<PoolObject<T>> tmp = new ArrayList<>();
            pool.drainTo(tmp);
            for (PoolObject<T> poolObject : tmp) {
                defaultInvalidateConsumer.accept(poolObject.getObject());
            }
        }

    }


    /**
     * ============================================================================================
     * Contains info about an used object used by one thread.
     */
    static final class UsedObject<T> {
        final WeakReference<Thread> threadReference;
        private final PoolObject<T> poolObject;
        private final long reservationTimestampInMilliSeconds;

        /**
         * A flag indicating if object has been invalidated or not. If true the object may be
         * returned to pool when released.
         */
        private boolean isValid = true;

        private UsedObject(PoolObject<T> poolObject, Thread thread) {
            Preconditions.checkNotNull(poolObject);
            this.poolObject = poolObject;
            threadReference = new WeakReference<>(thread);
            reservationTimestampInMilliSeconds = System.currentTimeMillis();
        }

        /**
         * Flag that, when this object is returned to pool, it cannot be reused by other clients.
         * The pool can then delete this instance, and allow another object to be created.
         */
        protected synchronized void invalidate() {
            isValid = false;
        }

        synchronized boolean isValid() {
            return isValid;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            UsedObject<?> that = (UsedObject<?>) o;
            Thread thisThread = threadReference.get();
            Thread thatThread = that.threadReference.get();
            return Objects.equals(thisThread, thatThread) &&
                    Objects.equals(this.poolObject, that.poolObject);
        }

        @Override
        public int hashCode() {
            return Objects.hash(threadReference.get(), poolObject);
        }

        Thread getThread() {
            return threadReference.get();
        }

        PoolObject<T> getPoolObject() {
            return poolObject;
        }

        long getReservationTimestampInMilliSeconds() {
            return reservationTimestampInMilliSeconds;
        }
    }
}
