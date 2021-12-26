package socket.aio.callback.server;

public class Server {
    private static int DEFAULT_PORT = 12345;
    private static AsyncServerHandler serverHandle;
    public volatile static long clientCount = 0;

    public static void start() {
        start(DEFAULT_PORT);
    }

    public static synchronized void start(int port) {
        if (serverHandle != null)
            return;
        serverHandle = new AsyncServerHandler(port);

        //
        Thread td = new Thread(serverHandle, "Server");
        td.setDaemon(true);
		td.start();
    }

    public static void main(String[] args) {
        Server.start();
    }
}