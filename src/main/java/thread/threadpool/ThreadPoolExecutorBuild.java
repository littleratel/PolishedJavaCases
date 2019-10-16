package thread.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorBuild {

	public static void main(String[] args) {
		ThreadPoolExecutor executorService = buildThreadPoolExecutor();

		long startTime = System.currentTimeMillis();

		executorService.execute(new Runnable() {
			public void run() {
				sleepSecond(10);
			}
		});

		// try {
		// TimeUnit.SECONDS.sleep(8);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }

		executorService.execute(new Runnable() {
			public void run() {
				sleepSecond(10);
			}
		});

		executorService.execute(new Runnable() {
			public void run() {
				sleepSecond(10);
			}
		});

		executorService.shutdown();
		int activeCount = -1;
		int queueSize = -1;

		while (true) {
			if (executorService.isTerminated()) {
				long endTime = System.currentTimeMillis();
				System.out.println("Executor " + (endTime - startTime) / 1000 + " seconds");
				return;

			}
			if (activeCount != executorService.getActiveCount() || queueSize != executorService.getQueue().size()) {
				System.out.println("The current ACTIVE thread num: " + executorService.getActiveCount());
				System.out.println("The current CORE   thread num: " + executorService.getCorePoolSize());
				System.out.println("The current QUEUE thread num: " + executorService.getQueue().size());
				System.out.println("The current MAX thread num: " + executorService.getMaximumPoolSize());

				System.out.println("===");
				activeCount = executorService.getActiveCount();
				queueSize = executorService.getQueue().size();
				System.out.println("=========================");
			}
		}
	}

	/**
	 * 1.coreSize=1,maxSize=2,blockingQueue=1,what happen when submit 3 tasks?
	 * 2.coreSize=1,maxSize=2,blockingQueue=1,what happen when submit 4 tasks?
	 * 3.coreSize=1,maxSize=2,blockingQueue=1,what happen when submit 6 tasks?
	 * 4.coreSize=maxSize 5.coreSize>maxSize int corePoolSize, int maximumPoolSize,
	 * long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue,
	 * ThreadFactory threadFactory, RejectedExecutionHandler handler
	 */
	private static ThreadPoolExecutor buildThreadPoolExecutor() {

		ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(1), new ThreadFactory() {

					// @Override
					public Thread newThread(Runnable r) {
						Thread t = new Thread(r);
						return t;
					}

				}, new ThreadPoolExecutor.AbortPolicy());
		// executor.allowCoreThreadTimeOut(false);
		System.out.println("The ThreadPoolExecutor create done.");
		return executor;
	}

	private static void sleepSecond(long seconds) {
		try {
			System.out.println("'" + Thread.currentThread().getName() + "' running task!");
			TimeUnit.SECONDS.sleep(seconds);
			System.out.println("'" + Thread.currentThread().getName() + "' complete the task!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
