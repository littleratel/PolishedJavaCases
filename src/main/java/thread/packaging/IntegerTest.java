package thread.packaging;

/**
 * 当为Integer赋值数值在-128~127区间时， 会从Integer中的一个Integer[]中获取一个缓存的Integer对象，
 * 而超出区间值得时候，每次都会new一个新的Integer对象，
 */
public class IntegerTest {
	// acc.hashCode = acc.value
	Integer acc = new Integer(0);
	Object obj = new Object();

	public void run() {
		synchronized (acc) {
			for (int i = 0; i < 5000; i++) {
				acc++;
			}
		}
	}

	public void print() {
		System.out.println("acc hashCode is:" + acc.hashCode());
		System.out.println("obj hashCode is:" + obj.hashCode());
		System.out.println("acc=" + acc);
	}

	// test
	public static void main(String[] args) throws InterruptedException {
		IntegerTest demo = new IntegerTest();
		demo.print();

		Runnable runable = new Runnable() {
			@Override
			public void run() {
				demo.run();
			}
		};

		Thread th1 = new Thread(runable);
		Thread th2 = new Thread(runable);
		th1.start();
		th2.start();

		th1.join();
		th2.join();

		demo.print();
	}
}
