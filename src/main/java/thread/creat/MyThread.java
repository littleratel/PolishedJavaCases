package thread.creat;

public class MyThread extends Thread {
	private String name;

	public MyThread(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println(name + "运行  :  " + i);
			try {
				sleep((int) Math.random()%10 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		MyThread mTh1 = new MyThread("A");
		MyThread mTh2 = new MyThread("B");
		mTh1.start();
		mTh2.start();
	}
}
