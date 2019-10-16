package cn.intv.multithread;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadsAlternationPrint2 {

	static AtomicInteger cxsNum = new AtomicInteger(0);
	static volatile boolean flag = false;

	public static void main(String[] args) {

		Thread t1 = new Thread(() -> {
			while (cxsNum.get() < 30) {
				if (!flag && cxsNum.incrementAndGet() % 2 != 0) {
					System.out.println(cxsNum.get());
					flag = true;
				}
			}
		});

		Thread t2 = new Thread(() -> {
			while (cxsNum.get() < 30) {
				if (flag && cxsNum.incrementAndGet() % 2 == 0) {
					System.out.println(cxsNum.get());
					flag = false;
				}
			}
		});

		t1.start();
		t2.start();
	}
}
