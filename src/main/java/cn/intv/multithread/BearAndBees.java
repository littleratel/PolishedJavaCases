package cn.intv.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 熊和蜜蜂的故事</br>
 * 有n个蜜蜂和1只熊，它们共享一个罐子，罐子容量为H初始为空；
 * 当罐子为空时，熊通知蜜蜂生产蜂蜜，然后熊去睡觉；之后蜜蜂生产蜂蜜，当生产满时，蜜蜂通知熊去吃蜂蜜，然后蜜蜂休息；
 * 每一个蜜蜂都会生产一份蜂蜜，最后一份放满罐子蜂蜜的蜜蜂去叫醒熊。
 */
public class BearAndBees {

	public static void main(String[] args) {
		Pot pot = new Pot();

		ExecutorService service = Executors.newFixedThreadPool(10);
		service.execute(new Thread(new Bee(pot), "bee1"));
		service.execute(new Thread(new Bee(pot), "bee2"));
		service.execute(new Thread(new Bee(pot), "bee3"));
		service.execute(new Thread(new Bee(pot), "bee4"));
		service.execute(new Thread(new Bee(pot), "bee5"));
		service.execute(new Thread(new Bee(pot), "bee6"));
		service.execute(new Thread(new Bee(pot), "bee7"));
		service.execute(new Thread(new Bear(pot), "bear1"));
		service.execute(new Thread(new Bear(pot), "bear2"));
		service.execute(new Thread(new Bear(pot), "bear3"));
		service.shutdown();
	}

	// class Bear
	static class Bear implements Runnable {
		private Pot pot;

		public Bear(Pot pot) {
			this.pot = pot;
		}

		@Override
		public void run() {
			while (true) {
				pot.reduceHoney();
			}
		}
	}

	// class Bees
	static class Bee implements Runnable {
		private Pot pot;

		public Bee(Pot pot) {
			this.pot = pot;
		}

		@Override
		public void run() {
			while (true) {
				pot.increaseHoney();
			}
		}
	}

	// class Pot
	static class Pot {
		private final int MAX_NUM = 1357;
		private Lock lock = new ReentrantLock();
		private Condition producer = lock.newCondition();
		private Condition consumer = lock.newCondition();
		private int honeyNum = 0;
		private boolean isBeesWork = true;

		public void increaseHoney() {
			lock.lock();
			try {
				while (!isBeesWork) {
					producer.await();
				}

				Thread.sleep(1000);
				honeyNum += 8;
				if (honeyNum >= MAX_NUM) {
					honeyNum = MAX_NUM;
				}
				System.out.println(Thread.currentThread().getName() + " 生产蜂蜜，现有: " + honeyNum);

				if (honeyNum == MAX_NUM) {
					isBeesWork = false;
					System.out.println("Pot is full of honey, call bear to eat.");
					consumer.signal();
					System.out.println("bees are going to sleep.");
					producer.await();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}

		public void reduceHoney() {
			lock.lock();
			try {
				while (isBeesWork) {
					consumer.await();
				}

				Thread.sleep(3000);
				honeyNum -= 26;
				if (honeyNum <= 0) {
					honeyNum = 0;
				}
				System.out.println(Thread.currentThread().getName() + " bear eat honey, surplus: " + honeyNum);

				if (honeyNum == 0) {
					System.out.println("pot is empty, call bees to produce.");
					isBeesWork = true;
					producer.signalAll();
					System.out.println("bear is going to sleep.");
					consumer.await();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
}
