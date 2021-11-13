package thread.lock;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    private final ReentrantLock lock = new ReentrantLock();
    private int i = 0;

    @Test
    public void mainTest() {
        RunnableLock instance = new RunnableLock();
        new Thread(instance, "T1").start();
        new Thread(instance, "T2").start();
        new Thread(instance, "T3").start();
    }

    class RunnableLock implements Runnable {
        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println("Get Queue Length: " + lock.getQueueLength());
                for (int j = 0; j < 1000; j++) {
                    i++;
                }
            } finally {
                lock.unlock();
            }
            System.out.println("i: " + i);
        }
    }
}
