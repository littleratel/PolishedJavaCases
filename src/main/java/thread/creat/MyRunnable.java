package thread.creat;

public class MyRunnable implements Runnable {

	public static void main(String[] args) {
		new Thread(new MyRunnable("C")).start();
		new Thread(new MyRunnable("D")).start();
	}

	private String name = null;

	public MyRunnable(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println("Thread " + this.name + " is running.");
	}
}
