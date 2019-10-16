package thread;

public class TestJoin {

	public static void main(String[] args) throws InterruptedException {
		// 测试join
//		testJoin();

		// 测试wait
		new Thread(new Thread1()).start();
		new Thread(new Thread2()).start();
	}

	private static void testJoin() throws InterruptedException {
		Thread td1 = new Thread(() -> {
			System.out.println("begin task 1");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("end task 1");
		});

		Thread td2 = new Thread(() -> {
			System.out.println("task 2");
		});

		Thread td3 = new Thread(() -> {
			System.out.println("task 3");
		});

		System.out.println("执行" + td1.getName());
		td1.start();
		td1.join(100);

		System.out.println("执行" + td2.getName());
		td2.start();
		td2.join();

		System.out.println("执行" + td3.getName());
		td3.start();
	}
	
	// 
	private static class Thread1 implements Runnable {
		@Override
		public void run() {
			synchronized (TestJoin.class) {
				System.out.println("enter thread1 ...");

				try {
					// wait 会释放锁
					System.out.println("thread1 call wait()");
					TestJoin.class.wait();
					// sleep 不释放锁
					System.out.println("thread1 call sleep()");
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("thread1 is being over!");
		}
	}

	private static class Thread2 implements Runnable {
		@Override
		public void run() {
			// notify方法并不释放锁，即使thread2调用了下面的sleep方法休息10ms，但thread1仍然不会执行
			// 因为thread2没有释放锁，所以Thread1得不到锁而无法执行
			synchronized (TestJoin.class) {
				System.out.println("enter thread2 ...");
				System.out.println("thread2 notify other thread...");
				TestJoin.class.notify();
			}

			System.out.println("thread2 is sleeping ten millisecond ...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("thread2 is being over!");
		}
	}

}
