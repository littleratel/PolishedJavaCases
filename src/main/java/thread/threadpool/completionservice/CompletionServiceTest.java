package thread.threadpool.completionservice;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletionServiceTest {

    @Test
    public void poolTest() {
        int poolSize = 5;
        int RESOURCE_SETUP_TIMEOUT = 10 * 60; //10 min

        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        CountingCompletionService<Void> countingCompletionService = new CountingCompletionService<>(executorService);

        for (int i = 1; i <= poolSize; i++) {
            int fin = i;
            countingCompletionService.submit(() -> {
                int timeout = 1000; // 10 min
                Thread.currentThread().setName("Setting-thread-" + fin);
                try {
                    if (fin == 1) {
                        timeout = 5 * timeout;
                    } else if (fin == 2) {
                        timeout = 10 * timeout;
                    } else if (fin == 3) {
                        timeout = 20 * timeout;
                    } else {
                        timeout = 10 * 60 * timeout;
                    }
                    Thread.sleep(timeout);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            });
        }

        try {
            countingCompletionService.waitServiceCompleted(RESOURCE_SETUP_TIMEOUT, "Resource Setup");
        } catch (CountingCompletionService.CompletionException e) {
            e.printStackTrace();
        }
    }

}
