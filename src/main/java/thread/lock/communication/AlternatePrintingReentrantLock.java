package thread.lock.communication;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AlternatePrintingReentrantLock {

    private volatile int cnt = 1;
    private final Lock lock = new ReentrantLock();
    private final Condition con = lock.newCondition();

    @Test
    public void mainTest() {
        new Thread(() -> {
            try {
                lock.lock();

                while (cnt < 100) {
                    System.out.println(cnt++);
                    con.signal();
                    con.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }).start();

        new Thread(() -> {
            try {
                lock.lockInterruptibly();  // 获取锁的过程中可响应中断

                while (cnt <= 100) {
                    System.out.println(cnt);
                    if (cnt == 100) {
                        con.signal();
                        break;
                    }

                    cnt++;
                    con.signal();
                    con.await();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        while (Thread.activeCount() > 1) {
            Thread.yield(); // 让出 CPU
        }

        System.out.println("Current: " + cnt);
    }
}