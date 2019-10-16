package threadlocal;

public class MyRunnable implements Runnable {

	Persion persion;
	ThreadLocalTest threadLocalTest;

	public MyRunnable(ThreadLocalTest threadLocal, Persion persion) {
		this.persion = persion;
		this.threadLocalTest = threadLocal;
	}

	@Override
	public void run() {
		threadLocalTest.set(persion);
		threadLocalTest.get();
	}
}
