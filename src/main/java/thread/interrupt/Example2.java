package thread.interrupt;

public class Example2 extends Thread {
	volatile boolean stop = false;

	public static void main(String args[]) throws Exception {
		Example2 thread = new Example2();
		thread.start();
		
		Thread.sleep(1000);
//		System.out.println("Set status to stop thread!");
//		thread.stop = true;// 如果线程阻塞，将不会检查此变量
		
		System.out.println("Call thread.interrupt()!");
		thread.interrupt();
		System.out.println("线程是否被中断?: "+thread.isInterrupted());
		
		Thread.sleep(3000);
		System.out.println("Stopping application...");
		// System.exit( 0 );
	}

	public void run() {
		while (!stop) {
			System.out.println("Thread running...");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println("Thread interrupted...");
			}
		}
		System.out.println("Thread exiting under request...");
	}

}
