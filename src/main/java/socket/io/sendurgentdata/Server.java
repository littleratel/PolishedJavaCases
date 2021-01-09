package socket.io.sendurgentdata;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final static int DEFAULT_PORT = 5209;

    public static void main(String[] args) {
        Server service = new Server();
        service.ServerBaseIO();
    }

    public void ServerBaseIO() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(DEFAULT_PORT);
            System.out.println("[Server]启动成功!");
        } catch (Exception e) {
            System.out.println("没有启动监听：" + e);
            e.printStackTrace();
        }

        Socket socket = null;
        BufferedReader read = null;
        PrintWriter writer = null;
        try {
            System.out.println("This just to verify whether the accept() is invoked before starting client!");
            socket = server.accept();
            System.out.println("Server accept finished!");

            // 获取输入流，字节流转换字符流, 并读取客户端信息
            read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
//            while (true) {
//                System.out.println("Get: " + read.readLine());
//            }

            socket.close();
            System.out.println(socket.isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(server, socket, read, writer);
        }
    }

    private static void close(ServerSocket server, Socket socket, BufferedReader read, PrintWriter writer) {
        try {
            if (read != null) {
                read.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (socket != null) {
                socket.close();
            }
            if (server != null) {
                server.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}