package thread.lock;

import java.util.concurrent.locks.LockSupport;

import com.google.common.base.Stopwatch;

public class LockSupportTest {
	public static void main(String[] args) {
		// testUnpark();
		// testParkTime();
		testUnparkSpecifiedThread();
	}

	private static void testUnpark() {
		final Thread currentThread = Thread.currentThread();
		LockSupport.unpark(currentThread);// 指定唤醒某个线程

		System.out.println("开始阻塞！");
		//这里不会阻塞;由于之前的unpark操作, 会抵消掉本次的park
		LockSupport.park(currentThread);
		System.out.println("结束阻塞！");
	}

	public static void testUnparkSpecifiedThread() {
		Thread thread = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " 开始执行.");
			LockSupport.park();
			System.out.println(Thread.currentThread().getName() + " 被唤醒.");
		}, "T1");
		thread.start();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 指定唤醒某个线程
		LockSupport.unpark(thread);
	}

	private static void testParkTime() {
		System.out.println("开始阻塞！");
		Stopwatch stopwatch = Stopwatch.createStarted();
		LockSupport.parkUntil(500);
		System.out.println(stopwatch.toString());
		System.out.println("结束阻塞！");
	}

}
