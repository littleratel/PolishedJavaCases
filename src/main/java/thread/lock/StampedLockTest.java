package thread.lock;

import org.junit.Test;

import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {
    private final StampedLock stampedLock = new StampedLock();
    private double x;
    private double y;

    @Test
    public void mainTest() {
        new Thread(() -> {
            move(3, 4);
        }).start();

        new Thread(() -> {
            double res = distanceFromOrigin();
            System.out.println(res);
        }).start();

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }

    public void move(double deltaX, double deltaY) {
        long stamp = stampedLock.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    public double distanceFromOrigin() {
        double currentX, currentY;
        long stamp;
        for (int i = 0; i < 3; i++) { // CAS
            stamp = stampedLock.tryOptimisticRead(); // 获得一个乐观读锁
            currentX = x;
            currentY = y;
            if (stampedLock.validate(stamp)) {
                return Math.sqrt(currentX * currentX + currentY * currentY);
            } else {
                continue;
            }
        }

        stamp = stampedLock.readLock(); // 获取一个悲观读锁
        try {
            currentX = x;
            currentY = y;
        } finally {
            stampedLock.unlockRead(stamp); // 释放悲观读锁
        }

        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
}