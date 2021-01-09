package thread.runnable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MyCallableTask implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		Thread.sleep(6000);
		return 100;
	}

	// 基于线程池的方式
	private static void baseThreadPool() throws InterruptedException, ExecutionException, TimeoutException {
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		// 一个线程对应着一个future
		Future<Integer> future = threadPool.submit(new MyCallableTask());

		System.out.println("before  call() status of thread : " + future.isDone());

		// get方法是阻塞的，即：线程无返回结果，get方法会一直等待。
		// 超时等待5s，超过则抛异常
		System.out.println("sum of the result is :" + future.get(5, TimeUnit.SECONDS));

		// 展示执行完成后，取消线程并查看其结果
		future.cancel(true);
		System.out.println("is canceled successful? " + future.isCancelled());
		System.out.println("after call() status of thread : " + future.isDone());

		threadPool.shutdown();
	}

	/**
	 * FutureTask可用于异步获取执行结果或取消执行任务的场景
	 * */ 
	private static void baseFutureTask() throws InterruptedException, ExecutionException {
		FutureTask<Integer> ft = new FutureTask<Integer>(new MyCallableTask());
		new Thread(ft).start();

		System.out.println("call isDone? : " + ft.isDone());
		System.out.println("call get : " + ft.get());
		System.out.println("call isDone? : " + ft.isDone());
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		baseThreadPool();
		baseFutureTask();
	}
}
