package thread.lock.communication;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

public class AlternatePrintingWaitAndNotify {

    private volatile int cnt = 1;
    private Object lock = new Object();

    @Test
    public void mainTest() {
        new Thread(() -> {
            synchronized (lock) {
                try {
                    while (cnt < 100) {
                        System.out.println(cnt++);
                        lock.notify();
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock) {
                try {
                    while (cnt <= 100) {
                        System.out.println(cnt);
                        if (cnt == 100) {
                            lock.notify();
                            break;
                        }

                        cnt++;
                        lock.notify();
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        while (Thread.activeCount() > 1) {
            Thread.yield(); // 让出 CPU
        }

        System.out.println("Current: " + cnt);

        LockSupport.parkUntil(100);
    }
}