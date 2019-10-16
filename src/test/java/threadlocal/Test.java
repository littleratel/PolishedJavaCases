package threadlocal;

public class Test {

	public static void main(String[] args) {
		ThreadLocalTest test = new ThreadLocalTest();

		Persion pite = new Persion("pite", 20, true);
		// Persion jhon = new Persion("jhon", 18, false);

		System.out.println(pite);
		new Thread(new MyRunnable(test, pite)).start();
		new Thread(new MyRunnable(test, pite)).start();
	}

}
