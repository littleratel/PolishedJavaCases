package thread.daemon;

import java.util.concurrent.TimeUnit;

public class DaemonThread {

    public static void main(String[] args) throws InterruptedException {

        // 设置一个钩子线程，在 JVM 退出时输出日志
        Runtime.getRuntime()
                .addShutdownHook(new Thread(() -> System.out.println("The JVM exit success !!!")));

        Thread thread = new Thread(() -> {
            // 模拟非守护线程不退出的情况
            while (true) {
                try {
                    // 睡眠一秒
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("I am running ...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.setDaemon(true);
        thread.start();

        TimeUnit.SECONDS.sleep(2);

        System.out.println("The main thread ready to exit..");
    }
}