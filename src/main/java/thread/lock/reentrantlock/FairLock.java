package thread.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

// Not entirely fair
public class FairLock {
    private static final ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        Runnable runnable = () -> {
            lock.lock();

            try {
                Thread.sleep(100);
                System.out.println("线程: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }
}
