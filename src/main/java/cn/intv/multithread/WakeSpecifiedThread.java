package cn.intv.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 唤醒指定的线程
public class WakeSpecifiedThread {

	static Lock lock = new ReentrantLock();
	static int count = 0;
	static Condition conditionA = lock.newCondition();
	static Condition conditionB = lock.newCondition();

	public static void main(String[] args) {

		Thread t1 = new Thread(() -> {
			lock.lock();

			if (count < 5) {
				System.out.println("线程1未达到业务要求,暂停中,等待线程2处理到达到要求后唤醒");
				try {
					conditionA.await();
					System.out.println("conditionA被唤醒");
					conditionB.await();
					System.out.println("conditionB被唤醒.");
					System.out.println("我是线程1后面的代码!");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			lock.unlock();
		});

		//
		Thread t2 = new Thread(() -> {
			lock.lock();

			while (count < 10) {
				count++;
				System.out.println("线程2业务处理中: " + count);
				try {
					Thread.sleep(1000);
					if (count == 5) {
						conditionA.signal();
						System.out.println("唤醒线程1");
						lock.unlock();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			try {
				lock.lock();// java.lang.IllegalMonitorStateException
				System.out.println("等待3秒后conditionB会被唤醒");
				Thread.sleep(3000);
				conditionB.signal();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lock.unlock();
		});

		t1.start();
		t2.start();
	}// main end

}
