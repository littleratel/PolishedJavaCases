package juc.cas;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {
	public static void main(String[] args) {

		final AtomicReference<Integer> money = new AtomicReference<>();
		money.set(19);

		for (int i = 0; i < 3; i++) {
			new Thread() {
				public void run() {
					while (true) {
						while (true) {
							Integer m = money.get();
							if (m < 20) {
								if (money.compareAndSet(m, m + 20)) {
									System.out.println(Thread.currentThread().getName()+"充值成功。余额：" + money.get() + "元");
									break;
								}
							} else {
								System.out.println("余额大于20，无需充值！");
								break;
							}
						}
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}.start();
		}

		new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					while (true) {
						Integer m = money.get();

						if (m > 10) {
							if (money.compareAndSet(m, m - 10)) {
								System.out.println("消费线程--成功消费10，卡余额：" + money.get());
								break;
							}
						} else {
							System.out.println("消费线程--余额不足！");
							break;
						}
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}// end 
}