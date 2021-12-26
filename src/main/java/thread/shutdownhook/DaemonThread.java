package thread.shutdownhook;

import org.junit.Test;
import util.SleepUtil;

public class DaemonThread {

    @Test
    public void main() {

        // Add a ShutdownHook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("The JVM exit success !!!")));

        Thread thread = new Thread(() -> {
            while (true) {
                SleepUtil.sleep(1);
                System.out.println("I am running ...");
            }
        });

        thread.setDaemon(true);
        thread.start();

        SleepUtil.sleep(2);

        System.out.println("The main thread ready to exit..");
    }
}