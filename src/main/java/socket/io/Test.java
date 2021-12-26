package socket.io;

import java.io.IOException;
import java.util.Random;

public class Test {

	public static void main(String[] args) throws InterruptedException {

		startServer();
		
		// 避免客户端先于服务器启动前执行代码
		Thread.sleep(1000);
		
		startClient();
	}

	private static void startServer() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// ServerNormal.start();
					//
					ServerBetter.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private static void startClient() {
		char operators[] = { '+', '-', '*', '/' };
		Random random = new Random(System.currentTimeMillis());
		Thread thread1 = new Thread(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				for (int t = 1; t < 3; t++) {
					// 随机产生算术表达式
					String expression = random.nextInt(10) + "" + operators[random.nextInt(4)]
							+ (random.nextInt(9) + 1);
					Client.send(expression);
					try {
						Thread.currentThread().sleep(random.nextInt(2000) + 1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		Thread thread2 = new Thread(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				for (int t = 1; t < 5; t++) {
					// 随机产生算术表达式
					String expression = random.nextInt(10) + "" + operators[random.nextInt(4)]
							+ (random.nextInt(9) + 1);
					Client.send(expression);
					try {
						Thread.currentThread().sleep(random.nextInt(1000) + 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		Thread thread3 = new Thread(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				for (int t = 1; t < 3; t++) {
					// 随机产生算术表达式
					String expression = random.nextInt(10) + "" + operators[random.nextInt(4)]
							+ (random.nextInt(9) + 1);
					Client.send(expression);
					try {
						Thread.currentThread().sleep(random.nextInt(2000) + 2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		thread1.start();
		thread2.start();
		thread3.start();
	}
}
