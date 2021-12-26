package socket.io.inputstream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ClientReadLine {
    private final static int DEFAULT_PORT = 5209;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket("127.0.0.1", DEFAULT_PORT);
                OutputStream ou = socket.getOutputStream();
        ) {
            System.out.println("[Client]启动成功!");

            BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String res = read.readLine();
            System.out.println(res);

            res = read.readLine();
            System.out.println(res);

            for (int i = 1; i < 5; i++) {
                ou.write(11);
            }
            ou.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isConnected(InputStream in, OutputStream ou) {
        try {
            int i = 0, r = 0;
            while (in.available() > 0) {
                while ((r = in.read()) != -1) {
                    System.out.println(i++ + " : " + r);
                }
            }

            r = in.read();
            if (in.available() <= 0 && r == -1) {
                return false;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

}