package socket.nio.demo2;

import java.util.Scanner;

public class Client {
	private static String DEFAULT_HOST = "127.0.0.1";
	private static int SERVER_PORT = 12345;
	private static ClientHandle clientHandle;

	public static void start() {
		start(DEFAULT_HOST, SERVER_PORT);
	}

	public static synchronized void start(String ip, int port) {
		if (clientHandle != null)
			clientHandle.stop();
		
		clientHandle = new ClientHandle(ip, port);
		System.out.println("[Client]Start! Port is: " + port);
		new Thread(clientHandle, "Thread-Client").start();
	}

	// 向服务器发送消息
	public static boolean sendMsg(String msg) throws Exception {
		if (msg.equals("q"))
			return false;
		
		System.out.println("[Client]["+Thread.currentThread().getName()+"]Send calculate formula: " + msg);
		clientHandle.sendMsg(msg);
		return true;
	}

	public static void stop() {
		clientHandle.stop();
	}

	public static void main(String[] args) throws Exception {
		start();
		while (sendMsg(new Scanner(System.in).nextLine()))
			;
	}
}
