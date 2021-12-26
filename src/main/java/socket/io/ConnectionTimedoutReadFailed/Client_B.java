package socket.io.ConnectionTimedoutReadFailed;

import util.TimeUtil;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client_B {
    private final static int DEFAULT_PORT = 5209;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket("127.0.0.1", DEFAULT_PORT);
                OutputStream out = socket.getOutputStream();
                PrintWriter write = new PrintWriter(out);
        ) {
            System.out.println("[Client]启动成功!");

            send(write);
            TimeUtil.sleepInMinutes(60);

            System.out.println("send to server after sleep 30 minutes.");
            send(write);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void send(PrintWriter write) {
        String cmd = "Client_B send cmd.";
        write.println(cmd);
        write.flush();
    }
}