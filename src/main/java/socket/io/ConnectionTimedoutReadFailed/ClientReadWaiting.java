package socket.io.ConnectionTimedoutReadFailed;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;

public class ClientReadWaiting {
    private final static int DEFAULT_PORT = 5209;

    private static final StringBuilder readDataBuffer = new StringBuilder();
    private static final int BUFFER_SIZE = 1024 * 2;
    private static final byte[] byteBuffer = new byte[BUFFER_SIZE];

    public static void main(String[] args) {
        try (
                Socket socket = new Socket("127.0.0.1", DEFAULT_PORT);
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                PrintWriter write = new PrintWriter(out);
        ) {
            System.out.println("[Client]Started, Start ex-change hello message.");
            write.println("Hello Server, Here is Client!");
            write.flush();

            while (true) {
                if (read(in)) {
                    break;
                }
                readDelaySleep();
            }

            System.out.println("Client get msg: " + readDataBuffer.toString());

            System.out.println("Client Stopped!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static boolean read(InputStream in) {
        try {
            int available = in.available();
            if (available <= 0) {
                return false;
            }

            int read = in.read(byteBuffer, 0, Math.min(available, BUFFER_SIZE));

            if (read != -1) {// workaround for those server who return available=1 but read=-1
                String readString = new String(byteBuffer, 0, read, Charset.defaultCharset());
                readDataBuffer.append(readString);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    static void readDelaySleep() {
        try {
            Thread.sleep(1000);
        } catch (final InterruptedException e) { //NOSONAR
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}