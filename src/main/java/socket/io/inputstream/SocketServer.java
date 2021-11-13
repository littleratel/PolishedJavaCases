package socket.io.inputstream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 搭建服务器端: <br>
 * a) new ServerSocket对象->绑定->监听端口<br>
 * b) accept() 等待客户端连接，获取连接的socket<br>
 * c) 获取IO流, socket.getInputStream, socket.getOutputStream<br>
 * d) 通过IO流与client交互<br>
 * e) 关闭相关资源<br>
 */
public class SocketServer {
    private final static int DEFAULT_PORT = 5209;

    public static void main(String[] args) throws IOException {
        SocketServer service = new SocketServer();
        service.ServerBaseIO();
    }

    public void ServerBaseIO() {
        try {
            ServerSocket server = null;
            try {
                server = new ServerSocket(DEFAULT_PORT);
                System.out.println("[Server]启动成功!");
            } catch (Exception e) {
                System.out.println("没有启动监听：" + e);
            }

            Socket socket = null;
            try {
                // 服务器socket接收到客户端socket请求，被动打开，开始接收客户端请求，直到客户端返回连接信息.
                // 这时候socket进入阻塞状态，即accept()方法一直客户端连接成功后才返回.
                System.out.println("This just to verify whether the accept() is invoked before starting client!");
                socket = server.accept();
                System.out.println("Server accept finished!");
            } catch (Exception e) {
                System.out.println("Error." + e);
            }

            // 获取输入流，字节流转换字符流, 并读取客户端信息
            BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Receive form Client:" + read.readLine());

            // input from console
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String readline = br.readLine();
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            while (!readline.equals("\r\n")) {
                writer.println(readline);
                writer.flush();
                System.out.println("Client:" + read.readLine());
                readline = br.readLine();
            }

            read.close();
            writer.close();
            socket.close();
            server.close();
        } catch (Exception e) {
            System.out.println("Error." + e);
        }
    }
}