package socket.nio.demo3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class SocketClient {
    private final static int DEFAULT_PORT = 1234;
    private static OutputStream ou;
    private static InputStream in;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", DEFAULT_PORT);
            ou = socket.getOutputStream();
            in = socket.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("[Client]启动成功!");

        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] readBuffer = new byte[1024];
                try {
                    while (true) {
                        int readBytes = in.read(readBuffer);
                        if (readBytes < 0) {
                            System.out.println("get readBytes:" + readBytes);
                        } else {
                            String body = new String(readBuffer, 0, readBytes, "UTF-8");
                            System.out.println("Now is : " + body);
                            Arrays.fill(readBuffer, (byte) 0);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] req = "QUERY-TIME-ORDER".getBytes();

                for (int i = 0; i < 3; i++) {
                    try {
                        ou.write(req);
                        ou.flush();

                        Thread.sleep(3000);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}