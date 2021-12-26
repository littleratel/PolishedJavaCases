package jvm.shutdownhook;

public class ShutdownHook {

	public void hook() {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("Execute Hook.....");
		}));
	}
	
//	public void hook_bk() {
//		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
//			@Override
//			public void run() {
//				System.out.println("Execute Hook.....");
//			}
//		}));
//	}

}
