package socket.io.inputstream;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ClientReadByByte {
    private final static int DEFAULT_PORT = 5209;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket("127.0.0.1", DEFAULT_PORT);
                InputStream in = socket.getInputStream();
        ) {
            System.out.println("[Client]启动成功!");
            //  socket.setSoTimeout(1); // read的超时时间

            int i = 0, r = 0;
            while (true) {
                r = in.read();
                System.out.println(i++ + " : " + r);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isConnectedReadVerify(InputStream in, StringBuilder outputBuffer) {
        try {
            int ch;
            if (in.available() > 0) {
                while ((ch = in.read()) != -1) {
                    outputBuffer.append((char) ch);
                }
            }
            if ((ch = in.read()) != -1) {
                outputBuffer.append((char) ch);
                return true;
            }
        } catch (SocketTimeoutException ste) {
            return true;
        } catch (IOException e) {
            
        }

        return false;
    }
}