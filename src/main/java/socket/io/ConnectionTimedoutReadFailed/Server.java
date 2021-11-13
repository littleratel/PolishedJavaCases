package socket.io.ConnectionTimedoutReadFailed;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.Charset;

public class Server {
    private final static int DEFAULT_PORT = 5209;
    private static final StringBuilder readDataBuffer = new StringBuilder();
    private static final int BUFFER_SIZE = 1024 * 2;
    private static final byte[] byteBuffer = new byte[BUFFER_SIZE];

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

        try (InputStream in = socket.getInputStream()) {
            while (true) {
                if (read(in)) {
                    System.out.println("Server get msg: " + readDataBuffer.toString());
                    readDataBuffer.setLength(0);
                }
                readDelaySleep();
            }
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