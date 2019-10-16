package socket.io.demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
	private final static int DEFAULT_PORT = 5209;
	
	public static void main(String[] args) throws IOException {
		clientBaseIO();
	}

	/**
	 * Implementation based on base IO
	 * new socket->connect指定主机端口
	 */
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
				// socket write
				write.println(readline);
				write.flush();

				// socket read
				System.out.println("Server:" + read.readLine());
				// re-input
				readline = br.readLine();
			}

			// close all
			write.close();
			read.close();
			socket.close();
		} catch (Exception e) {
			System.out.println("can not listen to:" + e);
		}
	}
}