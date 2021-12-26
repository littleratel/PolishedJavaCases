package socket.nio.demo2;

public class Server {
	private static int DEFAULT_PORT = 12345;
	private static ServerHandle serverHandle;

	public static void start() {
		start(DEFAULT_PORT);
	}

	public static synchronized void start(int port) {
		if (serverHandle != null)
			serverHandle.stop();
		
		serverHandle = new ServerHandle(port);
		System.out.println("[Server]Start! Port is: " + port);
		new Thread(serverHandle, "Thread-Server").start();
	}

	public static void stop() {
		serverHandle.stop();
	}

	public static void main(String[] args) {
		start();
	}
}
