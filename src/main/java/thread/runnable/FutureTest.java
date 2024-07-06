package thread.runnable;

import org.junit.Test;
import util.ThreadUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.lang.Thread.sleep;

public class FutureTest {

    ExecutorService executor = Executors.newSingleThreadExecutor();


    @Test
    public void testFutureTest() {
        Future<?> future = executor.submit(() -> {
            System.out.println("Current Thread: " + Thread.currentThread().getName());

            for (int i = 0; i < 100; i++) {
                try {
                    sleep(1_000);
                    System.out.println("isInterrupted: " + Thread.currentThread().isInterrupted());
                } catch (InterruptedException e) {
                    System.out.println("catch isInterrupted: " + Thread.currentThread().isInterrupted());
                    e.printStackTrace();
                } finally {
                    System.out.println("finally isInterrupted: " + Thread.currentThread().isInterrupted());
                }
            }
        });

        //
        long start = System.currentTimeMillis();

        boolean res = future.isCancelled();
        System.out.println("0: " + res);

        res = future.cancel(true);
        System.out.println("1: " + res);
        try {
            for (int i = 0; i < 20; i++) {
                res = future.isCancelled();
                System.out.println("-----> " + res);
                sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("End future.get, use time: " + (end - start));

    }
}
