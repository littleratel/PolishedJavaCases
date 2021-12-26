package juc.aqs;

import java.util.concurrent.CountDownLatch;

/**
 * 一个典型应用场景就是启动一个服务时，主线程需要等待多个组件加载完毕，之后再继续执行。
 * 
 */
public class CountDownLatchTest {

	public static void main(String[] args) {
		final CountDownLatch latch = new CountDownLatch(3);

		new Thread() {
			public void run() {
				try {
					System.out.println("子线程 " + Thread.currentThread().getName() + " 正在执行");
					Thread.sleep(1000);
					System.out.println("子线程 " + Thread.currentThread().getName() + " 执行完毕");
					latch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();

		new Thread() {
			public void run() {
				try {
					System.out.println("子线程 " + Thread.currentThread().getName() + " 正在执行");
					Thread.sleep(5000);
					System.out.println("子线程 " + Thread.currentThread().getName() + " 执行完毕");
					latch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();

		new Thread() {
			public void run() {
				try {
					System.out.println("子线程 " + Thread.currentThread().getName() + " 正在执行");
					Thread.sleep(3000);
					System.out.println("子线程 " + Thread.currentThread().getName() + " 执行完毕");
					latch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();

		try {
			System.out.println("等待3个子线程都执行完毕...");
			// 等到所有子线程都执行完后(即state=0)，会unpark()主调用线程，然后主调用线程就会从await()返回，继续后余动作。
			latch.await();
			// 等待超时，针对某些业务场景，如果某一个线程的操作耗时非常长或者发生了异常,但并不想影响主线程的继续执行,
			// latch.await(2000,TimeUnit.SECONDS);
			System.out.println("3个子线程已经执行完毕！继续执行主线程！");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}