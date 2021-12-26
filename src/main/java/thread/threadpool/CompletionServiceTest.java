package thread.threadpool;

import org.junit.Test;
import util.SleepUtil;

import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletionServiceTest {

    @Test
    public void main() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CompletionService completionService = new ExecutorCompletionService(executor);

        for (int i = 1; i <= 10; i++) {
            final int result = i;
            completionService.submit(() -> {
                SleepUtil.sleepInMillis(new Random().nextInt(1000));
                return result;
            });
        }

        // Get results
        try {
            System.out.println(completionService.take().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
