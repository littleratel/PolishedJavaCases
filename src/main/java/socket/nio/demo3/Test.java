package socket.nio.demo3;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        String host = "127.0.0.1";
        int port = 1234;
        String threadName = "NIO-Client-";

        for (int i = 1; i < 5; i++) {
            new Thread(new ClientHandle(host, port), threadName+i).start();
            Thread.sleep(1000);
        }
    }
}