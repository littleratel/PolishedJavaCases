package socket.io.inputstream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
	private final static int DEFAULT_PORT = 5209;
	public static void main(String[] args) {
		clientBaseIO();
	}

	private static void clientBaseIO() {
		try {
			Socket socket = new Socket("127.0.0.1", DEFAULT_PORT);
			System.out.println("[Client]启动成功!");

			// input from console
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String readline = br.readLine();

			// socket can read and write
			BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter write = new PrintWriter(socket.getOutputStream());

			while (!readline.equals("\r\n")) {
				write.println(readline);
				write.flush();
				System.out.println("Server:" + read.readLine());
				readline = br.readLine();
			}

			write.close();
			read.close();
			socket.close();
			System.out.println("isConnected: " + socket.isClosed());
		} catch (Exception e) {
			System.out.println("can not listen to:" + e);
		}
	}
}