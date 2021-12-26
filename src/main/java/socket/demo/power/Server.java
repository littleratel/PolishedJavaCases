package socket.demo.power;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    public static void main(String[] args) {
        Server service = new Server();
        service.ServerBaseIO();
    }

    public void ServerBaseIO() {
        try {
            ServerSocket server = null;
            try {
                server = new ServerSocket(5209);
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

            InputStream in = socket.getInputStream();
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            boolean flag = true;
            String response;
            while(flag){
                response = getResponse(in);
//                if(response.contains("ea")){
                    writer.println("Reply client: "+response);
                    writer.flush();
//                }
            }

            // close all
            writer.close();
            socket.close();
            server.close();
        } catch (Exception e) {
            System.out.println("Error." + e);
        }
    }

    private static String getResponse(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try {
            for (int idx = 0; in.available() <= 0 && idx < 60; idx++) {
                Thread.sleep(1000);
            }

            while (in.available() > 0) {
                int i = in.read();
                sb.append(hex(i));
            }
            System.out.println("response: {}" + sb);
        } catch (Exception e) {
            System.out.println("Error when getting response {}" + e);
            return "Exception received";
        }

        return sb.toString();
    }

    private static String hex(int x) {
        String hx = "0" + Integer.toHexString(x);
        return hx.substring(hx.length() - 2);
    }
}