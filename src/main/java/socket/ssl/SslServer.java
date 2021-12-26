package socket.ssl;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * 搭建服务器端:
 * a) new ServerSocket对象->绑定->监听端口。
 * b) accept() 接收客户端请求。
 * c) 通过输入输出流读取客户端发送的请求信息。
 * d) 通过输出流向客户端发送请求信息。
 * e) 关闭相关资源。
 */
public class SslServer {
	private final static int DEFAULT_PORT = 5209;
	private static String SERVER_KEY_STORE = "/home/ezfanbi/workspace/ssl/test/server_ks";
	private static String SERVER_KEY_STORE_PASSWORD = "123123";
	private static final boolean isTwoWay = false;

	public static void main(String[] args) {
		System.setProperty("javax.net.ssl.trustStore", SERVER_KEY_STORE);
		// System.setProperty("javax.net.debug", "ssl,handshake");

		ServerBaseIO();
	}

	public static void ServerBaseIO() {
		ServerSocket server = null;
		try {
			KeyStore ks = KeyStore.getInstance("jceks");
			ks.load(new FileInputStream(SERVER_KEY_STORE), null);
			KeyManagerFactory kf = KeyManagerFactory.getInstance("SunX509");
			kf.init(ks, SERVER_KEY_STORE_PASSWORD.toCharArray());
			SSLContext context = SSLContext.getInstance("TLS");
			context.init(kf.getKeyManagers(), null, null);

			ServerSocketFactory factory = context.getServerSocketFactory();
			server = factory.createServerSocket(DEFAULT_PORT);

			((SSLServerSocket) server).setNeedClientAuth(isTwoWay);

			System.out.println("[Server]启动成功!");
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}

		// 服务器socket接收到客户端socket请求，被动打开，开始接收客户端请求，直到客户端返回连接信息.
		// 这时候socket进入阻塞状态，即accept()方法一直客户端连接成功后才返回.
		try (Socket socket = server.accept();
				BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				PrintWriter writer = new PrintWriter(socket.getOutputStream());) {
			System.out.println("Server accept finished!");

			// 获取输入流，字节流转换字符流, 并读取客户端信息
			System.out.println("Receive form Client:" + read.readLine());
			String readline = br.readLine();

			while (!readline.equals("\r\n")) {
				writer.println(readline);
				writer.flush();
				System.out.println("Client:" + read.readLine());
				// re-input
				readline = br.readLine();
			}
		} catch (Exception e) {
			System.out.println("Error." + e);
		}

		// close server
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}