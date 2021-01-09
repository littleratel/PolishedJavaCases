package connectionpool.eric.test;

import connectionpool.eric.LightweightConnectionPool;
import connectionpool.eric.LightweightConnectionPoolImpl;
import connectionpool.eric.ObjectFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

@Slf4j
public class ConnectionPoolTest {

    private final Consumer<Coli> noOpConsumer = object -> {
        // Do nothing
    };

    private String cmd = "Demo-Command-String";

    @Test
    public void testSingleObjectPool() throws InterruptedException {
        ObjectFactory<Coli> objectFactory = () -> {
            Coli coli = new Coli("Coli_ID");
            return coli;
        };

        final LightweightConnectionPool<Coli> pool = new LightweightConnectionPoolImpl<>(1,
                objectFactory, noOpConsumer);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            Coli coli = pool.take();
            String res = coli.send(cmd);
            log.info("Thread: {}, get response {} after sending cmd: {}", Thread.currentThread().getName(), res, cmd);
        });

        try {
            Assert.assertFalse(executorService.awaitTermination(1, TimeUnit.SECONDS));
        } finally {
            executorService.shutdownNow();
        }
    }

    @Test
    public void testMultiObjectPool() throws InterruptedException {
        int poolCapacity = 3;
        int numClients = 9;

        ObjectFactory<Coli> objectFactory = () -> {
            long objId = System.currentTimeMillis();
            Coli coli = new Coli("Coli_ID_" + objId);
            return coli;
        };

        final LightweightConnectionPool<Coli> pool = new LightweightConnectionPoolImpl<>(poolCapacity,
                objectFactory, noOpConsumer);

        ExecutorService executorService = Executors.newFixedThreadPool(numClients);
        try {
            for (int i = 0; i < numClients; i++) {
                final int finalI = i;

                executorService.submit(() -> {
                    try {
                        String clientName = "client-" + finalI;
                        Thread.currentThread().setName(clientName + "-thread");
                        Coli coli = pool.take();
                        String res = coli.send(cmd);
                        Thread.sleep(1000);
                        log.info("Thread: {}, get res: {} after sending cmd: {}", Thread.currentThread().getName(), res, cmd);
                        pool.put(coli);
                    } catch (InterruptedException | RuntimeException e) {
                        e.printStackTrace();
                    }
                }); // end executorService

            }
            executorService.shutdown();
            executorService.awaitTermination(3000, TimeUnit.SECONDS);
        } finally {
            List<Runnable> unstoppable = executorService.shutdownNow();
            log.info("ExecutorService ShutdownNow(): [{}]", unstoppable);
            Assert.assertTrue(unstoppable.isEmpty());
        }
    }

    @Test
    public void testInvalidate() {
        final String INVALIDATED = "This object has been invalidated by consumer provided in C:tor";

        final AtomicInteger count = new AtomicInteger(1);
        ObjectFactory<Coli> objectFactory = () -> new Coli("id" + count.getAndIncrement());
        final LightweightConnectionPool<Coli> pool = new LightweightConnectionPoolImpl<>(1,
                objectFactory, createConsumer(INVALIDATED));

        Coli take = pool.take();
        pool.invalidate(Thread.currentThread());

        Coli take2 = pool.take();
        pool.put(take2);
    }

    private static Consumer<Coli> createConsumer(final String invalidatedValue) {
        
        return null;
    }
}
