package util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadUtil {
    private static ExecutorService executor = ThreadUtil.getNamedDaemonCacheExecutorService(ThreadUtil.class);
    private static AtomicLong threadIdGenerator = new AtomicLong(1);

    public static Boolean executeWithTimeoutBoolean(Callable<Boolean> callable, int timeoutInMillis) {
        try {
            return executeWithTimeout(callable, timeoutInMillis);
        } catch (Throwable e) {
            return false;
        }
    }

    public static <T> T executeWithTimeout(Callable<T> callable, int timeoutInMillis) throws TimeoutException {
        final Future<T> future = executor.submit(callable);
        try {
            return future.get(timeoutInMillis, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            if (Error.class.isAssignableFrom(e.getCause().getClass())) {
                throw (Error) e.getCause();
            }
            throw (RuntimeException) e.getCause();
        } catch (InterruptedException e) {
            return null; //ignore because intentional
        }
    }

    /**
     * handle no return result task
     * */
    public static void executeWithTimeout(Runnable runnable, int timeoutInMillis) throws TimeoutException {
        final Future<?> future = executor.submit(runnable);
        try {
            future.get(timeoutInMillis, TimeUnit.MILLISECONDS);
            return;
        } catch (ExecutionException e) {
            if (Error.class.isAssignableFrom(e.getCause().getClass())) {
                throw (Error) e.getCause();
            }
            throw (RuntimeException) e.getCause();
        } catch (InterruptedException e) {
            return;
        }
    }

    private static ExecutorService getNamedDaemonCacheExecutorService(final Class<?> clazz) {
        return Executors.newCachedThreadPool(newThreadFactory(clazz));
    }


    public static ThreadFactory newThreadFactory(final Class<?> clazz) {
        return r -> {
            Thread thread = Executors.defaultThreadFactory().newThread(r);
            thread.setDaemon(true);
            setThreadName(thread);
            return thread;
        };
    }

    private static void setThreadName(Thread thread) {
        thread.setName("POOL-T-" + threadIdGenerator.getAndIncrement());
    }
}