package juc.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 假若有若干个线程都要写数据操作，并且只有所有线程都写完成之后，这些线程才能继续做后面的事情
 * 
 */
public class CyclicBarrierTest {

	public static void main(String[] args) {
		// test();
		testBarrierAction();
		// testReUse();
	}

	public static void test() {
		int N = 4;
		CyclicBarrier barrier = new CyclicBarrier(N);
		for (int i = 0; i < N; i++) {
			new Writer(barrier).start();
		}
	}

	/**
	 * test with barrierAction，最后一个到达barrier状态的线程去执行BarrierAction
	 */
	public static void testBarrierAction() {
		int N = 4;
		CyclicBarrier barrier = new CyclicBarrier(N, new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + " do BarrierAction!");
			}
		});

		for (int i = 0; i < N; i++)
			new Writer(barrier).start();
	}

	public static void testReUse() {
		int N = 4;
		CyclicBarrier barrier = new CyclicBarrier(N);

		for (int i = 0; i < N; i++) {
			new Writer(barrier).start();
		}

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("CyclicBarrier重用");

		for (int i = 0; i < N; i++) {
			new Writer(barrier).start();
		}
	}

	static class Writer extends Thread {
		private CyclicBarrier cyclicBarrier;

		public Writer(CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
		}

		@Override
		public void run() {
			System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
			try {
				Thread.sleep(2000); // 写操作
				System.out.println("线程" + Thread.currentThread().getName() + "写完成，等待其他线程写入完毕");
				cyclicBarrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "所有线程写入完毕，继续处理其他任务...");
		}
	}
}