package thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 锁降级: 指写锁降级为读锁
 */
public class ReentrantReadWriteLockTest3 {
    private final ReadWriteLock rwlock = new ReentrantReadWriteLock();
    private final Lock r = rwlock.readLock();
    private final Lock w = rwlock.writeLock();
    private volatile boolean update = false;

    public void lockDegradation() {
        r.lock();
        if (!update) {
            r.unlock();
            w.lock();
            System.out.println("Step 1");
            r.lock();
            w.unlock();
        }

        System.out.println("Step 2");
        r.unlock();
    }

    /**
     * 锁升级
     * 读锁没有释放的时候，获取到写锁，再释放读锁
     */
    public void lockEscalation() {
        r.lock();
        w.lock();
        System.out.println("Step 1");
        r.unlock();
        w.unlock();
        System.out.println("Step 2");
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockTest3 rwLock = new ReentrantReadWriteLockTest3();
//        rwLock.lockDegradation();
        rwLock.lockEscalation();
        System.out.println("END!!!");
    }
}
