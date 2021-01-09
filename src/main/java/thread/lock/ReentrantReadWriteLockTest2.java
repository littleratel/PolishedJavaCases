package thread.lock;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * 在写锁里可以获得读锁；反过来不行。
 * */
public class ReentrantReadWriteLockTest2 {
    private final ReadWriteLock rwlock = new ReentrantReadWriteLock();
    private final Lock rlock = rwlock.readLock();
    private final Lock wlock = rwlock.writeLock();
    private int[] counts = new int[10];

    public void inc(int index) {
        wlock.lock(); // 加写锁
        try {
            counts[index] += 1;
        } finally {
            wlock.unlock(); // 释放写锁
        }
    }

    public int[] get() {
        rlock.lock(); // 加读锁
        try {
            System.out.println(Thread.currentThread().getName()+" call get()...");
            inc(1); //
            return Arrays.copyOf(counts, counts.length);
        } finally {
            rlock.unlock(); // 释放读锁
        }
    }

    //
    public static void main(String[] args) {
        ReentrantReadWriteLockTest2 rwLock = new ReentrantReadWriteLockTest2();
//        rwLock.inc(0);
        System.out.println(rwLock.get());
    }
}