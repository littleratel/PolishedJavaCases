package socket.io.sendurgentdata;

import java.net.Socket;

public class Client {
    private final static int DEFAULT_PORT = 5209;

    public static void main(String[] args)  {
        clientBaseIO();
    }

    private static void clientBaseIO() {
        int count = 0;
        Socket socket = null;

        try {
            socket = new Socket("127.0.0.1", DEFAULT_PORT);
            System.out.println("[Client]启动成功!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                socket.sendUrgentData(0xFF);
                System.out.println(++count);
                Thread.sleep(500);
                System.out.println("count: " + count);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}