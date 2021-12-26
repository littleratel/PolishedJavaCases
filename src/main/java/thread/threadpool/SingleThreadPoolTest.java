package thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPoolTest {

	public static void main(String[] args) {
		ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 10; i++) {
			final int index = i;
			singleThreadPool.execute(new Runnable() {
				public void run() {
					if (index == 2 || index == 5) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}						
						throw new NumberFormatException();
					}
					System.out.println(Thread.currentThread().getName() + "执行 NO." + index);
				}
			});
		}
		singleThreadPool.shutdown();
	}

}
