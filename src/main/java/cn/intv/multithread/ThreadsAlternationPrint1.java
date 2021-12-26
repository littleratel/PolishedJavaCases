package cn.intv.multithread;

public class ThreadsAlternationPrint1 {

	public static void main(String[] args) {
		final ThreadsAlternationPrint1 demo2 = new ThreadsAlternationPrint1();
		Thread t1 = new Thread(demo2::print1);
		Thread t2 = new Thread(demo2::print2);

		t1.start();
		t2.start();
	}

	public synchronized void print1() {
		for (int i = 0; i <= 30; i += 2) {
			System.out.println(Thread.currentThread().getName() + " print: " + i);
			this.notifyAll();
			try {
				this.wait();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// NO
			}
		}
	}

	public synchronized void print2() {
		for (int i = 1; i <= 30; i += 2) {
			System.out.println(Thread.currentThread().getName() + " print: " + i);
			this.notifyAll();
			try {
				this.wait();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// NO
			}
		}
	}

}
