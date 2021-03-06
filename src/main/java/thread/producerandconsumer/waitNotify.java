package thread.producerandconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class waitNotify {

	private static final int CAPACITY = 10;

	public static void main(String args[]) {
		Buffer buff = new Buffer(CAPACITY);

		Thread producer1 = new Producer("P-1", buff);
		Thread producer2 = new Producer("P-2", buff);
		Thread producer3 = new Producer("P-3", buff);

		// Thread consumer1 = new Consumer("C-1", buff);
		// Thread consumer2 = new Consumer("C-2", buff);
		// Thread consumer3 = new Consumer("C-3", buff);

		producer1.start();
		producer2.start();
		producer3.start();

		// consumer1.start();
		// consumer2.start();
		// consumer3.start();
	}

	public static class Buffer {
		private Queue<String> queue = new LinkedList<String>();
		private int maxSize;

		public Buffer(int maxSize) {
			this.maxSize = maxSize;
		}

		public synchronized void put(final String name, final int e) {
			while (queue.size() >= maxSize) { // spinlock
				try {
					System.out.println("Queue is full, Producer[" + name + "] thread waiting for "
							+ "consumer to take something from queue.");
					this.wait();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			queue.offer(String.format("%d(%s)", e, name));
			System.out.println("[" + name + "] Producing value : +" + e + ", queue size is " + queue.size());
			this.notify(); // this.notifyAll()
		}

		public synchronized String get(final String name) {
			while (queue.isEmpty()) { // spinlock
				try {
					System.out.println("Queue is empty, Consumer[" + name + "] thread is waiting for Producer");
					this.wait();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			String e = queue.poll();
			System.out.println("[" + name + "] Consuming value : -" + e);
			this.notify(); // this.notifyAll()
			return e;
		}
	}

	public static class Producer extends Thread {
		private String name;
		private Buffer buff;

		public Producer(final String name, final Buffer buff) {
			this.name = name;
			this.buff = buff;
		}

		public void run() {
			int e = 1;
			while (true) {
				buff.put(name, e++);
				waitNotify.randomSleep();
			}

			// while (true) {
			// buff.put(name, getNextE());
			// waitNotifyEn.randomSleep();
			// }

		}

		private static int e = 0;

		private static synchronized int getNextE() {
			return e++;
		}
	}

	public static class Consumer extends Thread {
		private String name;
		private Buffer buff;

		public Consumer(final String name, final Buffer buff) {
			this.name = name;
			this.buff = buff;
		}

		public void run() {
			while (true) {
				buff.get(name);
				waitNotify.randomSleep();
			}
		}
	}

	private static void randomSleep() {
		try {
			Thread.sleep(new Random().nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
