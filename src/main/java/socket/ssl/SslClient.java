package socket.ssl;

import javax.net.SocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class SslClient {
	private static String host = "127.0.0.1";
	private final static int PORT = 5209;
	private static String CLIENT_KEY_STORE = "/home/ezfanbi/workspace/ssl/test/client_ks";
	private static String CLIENT_KEY_STORE_PASSWORD = "456456";
	private static final boolean isTwoWay = true;

	public static void main(String[] args) {
		// Set the key store to use for validating the server cert.
		System.setProperty("javax.net.ssl.trustStore", CLIENT_KEY_STORE);
		// System.setProperty("javax.net.debug", "ssl,handshake");

		clientBaseIO();
	}

	/**
	 * Implementation based on base IO new socket->connect指定主机端口
	 */
	private static void clientBaseIO() {
		try (Socket socket = clientWithCert(isTwoWay);
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				// socket can read and write
				BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter write = new PrintWriter(socket.getOutputStream());) {
			System.out.println("[Client]启动成功!");

			String readline = br.readLine();
			while (!readline.equals("\r\n")) {
				write.println(readline);
				write.flush();

				// socket read
				System.out.println("Server:" + read.readLine());
				// re-input
				readline = br.readLine();
			}
		} catch (Exception e) {
			System.out.println("can not listen to:" + e);
		}
	}

	private static Socket clientWithCert(boolean isTwoWay) {
		Socket socket = null;
		try {
			if (isTwoWay) {
				return SSLSocketFactory.getDefault().createSocket(host, PORT);
			}

			SSLContext context = SSLContext.getInstance("TLS");
			KeyStore ks = KeyStore.getInstance("jceks");

			ks.load(new FileInputStream(CLIENT_KEY_STORE), null);
			KeyManagerFactory kf = KeyManagerFactory.getInstance("SunX509");
			kf.init(ks, CLIENT_KEY_STORE_PASSWORD.toCharArray());
			context.init(kf.getKeyManagers(), null, null);

			SocketFactory factory = context.getSocketFactory();
			socket = factory.createSocket(host, PORT);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}

		return socket;
	}
}