package socket.nio.demo3;

public class Test {
	public static void main(String[] args) {
		int port = 1234;
		if (args != null && args.length > 0) {
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				// 采用默认值
			}
		}
		new Thread(new ClientHandle("127.0.0.1", port), "TimeClient-001").start();
	}
}