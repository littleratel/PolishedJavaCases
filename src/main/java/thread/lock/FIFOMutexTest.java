package thread.lock;

import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class FIFOMutexTest {

	private static FIFOMutex mutex = new FIFOMutex();

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				sleep(1000 * 2);
				System.out.println(new Date().toGMTString() + " :t1 准备lock");
				mutex.lock();
				System.out.println(new Date().toGMTString() + " :t1 完成lock");
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				sleep(1000 * 3);
				System.out.println(new Date().toGMTString() + " :t2 准备lock");
				mutex.lock();
				System.out.println(new Date().toGMTString() + " :t2 完成lock");
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				sleep(1000 * 5);
				System.out.println(new Date().toGMTString() + " :t3 准备 unlock释放");
				mutex.unlock();
				System.out.println(new Date().toGMTString() + " :t3 释放完毕unlock");
			}
		}).start();
	}

	public static void sleep(long num) {
		try {
			Thread.sleep(num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static class FIFOMutex {
		private final AtomicBoolean locked = new AtomicBoolean(false);
		private final Queue<Thread> waiters = new ConcurrentLinkedQueue<Thread>();

		public void lock() {
			boolean wasInterrupted = false;
			// 首先获取当前线程，然后存入waiters 队列头部
			Thread current = Thread.currentThread();
			waiters.add(current);

			// 先循环判断是否满足线程挂起条件
			while (waiters.peek() != current || !locked.compareAndSet(false, true)) {
				// 把当前的FIFOMutex 对象传递到
				// LockSupport.park(this)，LockSupport会把this给赋值到当前Thread的变量里
				LockSupport.park(this);
				if (Thread.interrupted()) // ignore interrupts while waiting
					wasInterrupted = true;
			}
			// 上面那段while循环主要是负责阻塞挂起线程。当挂起条件不满足后则跳出循环
			// 删除队列头部已被阻塞完成的线程
			waiters.remove();
			if (wasInterrupted) // reassert interrupt status on exit
				current.interrupt();
		}

		// 释放线程
		public void unlock() {
			locked.set(false);
			LockSupport.unpark((Thread) waiters.peek());
		}
	}
}