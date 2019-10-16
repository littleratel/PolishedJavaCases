package thread.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest implements Runnable {

	public static ReentrantLock lock = new ReentrantLock();
	public static int i = 0;

	@Override
	public void run() {
		for (int j = 0; j < 1000; j++) {
			lock.lock(); // 看这里就可以
			lock.lock(); 
			i++;
			try {
				// i++;
			} finally {
				lock.unlock(); // 看这里就可以
				lock.unlock(); // 果这里代码注释，将进入死锁。 程序不会退出
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ReentrantLockTest test = new ReentrantLockTest();
		Thread t1 = new Thread(test);
		Thread t2 = new Thread(test);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.err.println(i);
	}
}
