package thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * 有这样一种场景： 对共享资源有读和写的操作，且写操作没有读操作那么频繁。
 * 在读操作时，多个线程同时读一个资源没有任何问题，所以应该允许多个线程同时读取共享资源；
 * 但是如果一个线程想去写这些共享资源，就不应该允许其他线程对该资源进行读和写的操作了。
 * <p>
 * 读写锁，读写分类: 读读共享，读写互斥，写写互斥
 */
public class ReentrantReadWriteLockTest {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReadLock readLock = lock.readLock();
    private WriteLock writeLock = lock.writeLock();

    public static void main(String[] args) {
        final ReentrantReadWriteLockTest rw = new ReentrantReadWriteLockTest();
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.execute(rwThread(rw, "R1"));
        service.execute(rwThread(rw, "R2"));
        service.execute(rwThread(rw, "R3"));
        service.execute(rwThread(rw, "R4"));
        service.execute(rwThread(rw, "W1"));
        service.shutdown();
    }

    public void read() {
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + " - Read begin..");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " - Read finish.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public void write() {
        try {
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + " - Write begin..");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " - Write finish.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    private static Thread rwThread(ReentrantReadWriteLockTest wr, String name) {
        if (name.charAt(0) == 'R') {
            return new Thread(new Runnable() {
                public void run() {
                    wr.read();
                }
            }, name);
        }

        return new Thread(new Runnable() {
            public void run() {
                wr.write();
            }
        }, name);
    }
}