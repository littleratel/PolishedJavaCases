package thread.threadlocal;

public class ThreadLocalTest {

	public final static ThreadLocal<String> threadLocal1 = new ThreadLocal<String>(){
		@Override
		protected String initialValue() {
			return "initialThreadLocal - default";
		}
	};
	public final static ThreadLocal<String> threadLocal2 = new ThreadLocal<String>();

	public static void setOne(String value) {
		threadLocal1.set(value);
	}
	public static void setTwo(String value) {
		threadLocal2.set(value);
	}

	public static void display() {
		System.out.println(threadLocal1.get() + " : " + threadLocal2.get());
	}

	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			final int index = i + 1;
			final String resouce1 = "线程 - " + index;
			final String resouce2 = "value = (" + index + ")[原值]";
			new Thread() {
				public void run() {
					try {
						display();
						setOne(resouce1);
						setTwo(resouce2);
						display();
						setOne("线程 - " + index);
						setTwo("value = (" + index + ")[修改]");
						display();
					} finally {
						threadLocal1.remove();
						threadLocal2.remove();
					}
				}
			}.start();
		}
	}
}
