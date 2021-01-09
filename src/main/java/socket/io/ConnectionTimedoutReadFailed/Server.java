package socket.io.ConnectionTimedoutReadFailed;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class Server {
    private final static int DEFAULT_PORT = 5209;

    public static void main(String[] args) {
        Server service = new Server();
        service.ServerBaseIO();
    }

    public void ServerBaseIO() {
        ServerSocket server = null;
        Socket socket = null;
        try {
            server = new ServerSocket(DEFAULT_PORT);
            System.out.println("[Server]启动成功!");
            System.out.println("This just to verify whether the accept() is invoked before starting client!");
            socket = server.accept();
            System.out.println("Server accept finished!");
        } catch (Exception e) {
            System.out.println("没有启动监听：" + e);
        }

        try {
            InputStream in = socket.getInputStream();

            BufferedReader read = new BufferedReader(new InputStreamReader(in));
            while (true) {
                System.out.println("Receive form Client:" + read.readLine());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}