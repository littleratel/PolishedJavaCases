package threadlocal;

public class ThreadLocalTest {

	private ThreadLocal<Persion> threadLocal = new ThreadLocal<Persion>();
	
//	public ThreadLocalTest(ThreadLocal<Persion> tl) {
//		this.threadLocal = tl;
//	}
//	
	public void set(Persion p) {
		threadLocal.set(p);
	}

	public void get() {
		System.out.println(threadLocal.get());
	}
}
