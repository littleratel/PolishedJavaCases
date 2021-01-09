package thread.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo implements Runnable {

    private static final ReentrantLockDemo instance = new ReentrantLockDemo();
    private final ReentrantLock lock = new ReentrantLock();
    private int i = 0;

    @Override
    public void run() {
        try {
            lock.lock(); // 看这里就可以
            System.out.println("Get Queue Length: " + lock.getQueueLength());
            for (int j = 0; j < 1000; j++) {
                i++;
            }
        } finally {
            lock.unlock(); // 果这里代码注释，将进入死锁。 程序不会退出
        }
        System.out.println("i: " + i);
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance, "T1");
        Thread t2 = new Thread(instance, "T2");
        Thread t3 = new Thread(instance, "T3");
        t1.start();
        t2.start();
        t3.start();
    }
}
