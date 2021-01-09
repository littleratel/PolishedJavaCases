package socket.io.demo1;

import java.io.InputStream;
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
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            System.out.println("start to send hello msg.");

//            for (int j = 0; j < 6; j++) {
//                out.write(j);
//            }
//            out.flush();

            System.out.println("<=== Close ===>");
            out.close();
            in.close();
            socket.close();

            SocketAddress address = new InetSocketAddress(socket.getInetAddress(), socket.getPort());
            socket.connect(address);

            System.out.println("<=== Socket Connection Restore ===>");
            socket.connect(socket.getRemoteSocketAddress());
            for (int j = 0; j < 6; j++) {
                out.write(j);
            }
            out.flush();

            server.close();
            System.out.println("server isConnected: " + socket.isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 为了防止 main 线程退出
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
        }
    }
}