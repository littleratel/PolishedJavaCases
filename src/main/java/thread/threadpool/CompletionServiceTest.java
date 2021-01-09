package thread.threadpool;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletionServiceTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		CompletionService completionService = new ExecutorCompletionService(executor);

		for (int i = 1; i <= 10; i++) {
			final int result = i;
			completionService.submit(new Callable<Integer>() {
				public Integer call() throws Exception {
					Thread.sleep(new Random().nextInt(1000));
					return result;
				}
			});
		}

		System.out.println(completionService.take().get());
	}
}
