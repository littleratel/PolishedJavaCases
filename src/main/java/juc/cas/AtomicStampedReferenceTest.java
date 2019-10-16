package juc.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceTest {

	public static void main(String[] args) {
		final AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19, 0);

		// 模拟多个线程更新数据库，为用户充值
		for (int i = 0; i < 3; i++) {
			final int timestamp = money.getStamp();

			new Thread() {
				public void run() {
					while (true) {
						while (true) {
							Integer m = money.getReference();
							if (m < 20) {
								if (money.compareAndSet(m, m + 20, timestamp, timestamp + 1)) {
									System.out.println(Thread.currentThread().getName()+"充值成功。余额：" + money.getReference() + "元");
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
		
		// 用户消费进程，模拟消费行为
		new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					while (true) {
						int timestamp = money.getStamp();
						Integer m = money.getReference();

						if (m > 10) {
							if (money.compareAndSet(m, m - 10, timestamp, timestamp + 1)) {
								System.out.println("消费线程--成功消费10，卡余额：" + money.getReference());
								break;
							}
						} else {
							System.out.println("余额不足！");
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
	}// end main()
}