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

    /**
     *
     *
     * */
    @Test
    public void testFutureTest() {
        Future<?> future = executor.submit(() -> {
            try {
                System.out.println("Current Thread: " + Thread.currentThread().getName());
                Thread.currentThread().interrupt();
                System.out.println("isInterrupted: " + Thread.currentThread().isInterrupted());
                sleep(5000); // 5s
                System.out.println("End Run().");
            } catch (InterruptedException e) {
                System.out.println("catch isInterrupted: " + Thread.currentThread().isInterrupted());
                e.printStackTrace();
            } finally {
//                Thread.currentThread().interrupt();
//                Thread.interrupted(); //
                System.out.println("finally isInterrupted: " + Thread.currentThread().isInterrupted());
            }
        });

        try {
            long start = System.currentTimeMillis();
            future.get(3, TimeUnit.SECONDS);
            long end = System.currentTimeMillis();
            System.out.println("End future.get, use time: " + (end - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
//            future.cancel(true);
            System.out.println("After call future.cancel");
            e.printStackTrace();
        }
    }
}
