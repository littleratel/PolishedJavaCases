package jvm.shutdownhook;

import java.util.concurrent.TimeUnit;

public class ShutdownHookTest {

	public static void main(String[] args) {
		new ShutdownHook().hook();

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("Execute Hook.....");
		}));

		System.out.println("The Application is doing something");
		// System.exit(0);
		// System.out.println("After call System.exit(0)");

		new Thread(() -> {
			while (true) {
				System.out.println(Thread.currentThread().getName() + " is running....");
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
