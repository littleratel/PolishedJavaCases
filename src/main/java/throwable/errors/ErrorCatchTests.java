package throwable.errors;

import org.junit.Test;

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;

public class ErrorCatchTests {

    @Test
    public void testCatchError() {
        int portNumber = 10001;

        boolean isPortAvailable = isAvailable(portNumber);
        System.out.println(isPortAvailable);
    }


    private boolean isAvailable(int portNumber) {
        ServerSocket ss = null;
        ServerSocket ssBk = null;
        DatagramSocket ds = null;
        try {
            ss = new ServerSocket(portNumber);
            ss.setReuseAddress(true);

            ssBk = new ServerSocket(portNumber);
            ssBk.setReuseAddress(true);

            ds = new DatagramSocket(portNumber);
            ds.setReuseAddress(true);
            System.out.println(portNumber + " is available");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeIO(ds);
            closeIO(ss);
            closeIO(ssBk);
        }
    }

    private void closeIO(Closeable closeable) {
        try {
            if (closeable == null)
                return;

            closeable.close();
            closeable = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
