package thread.lock;

import java.util.concurrent.locks.LockSupport;

import com.google.common.base.Stopwatch;

public class LockSupportTest {
    public static void main(String[] args) {
//        testParkTime();
        testMultipleUnparkBeforePark();
    }

    private static void testParkTime() {
        System.out.println("开始阻塞！");
        Stopwatch stopwatch = Stopwatch.createStarted();
        LockSupport.parkUntil(500);
        System.out.println(stopwatch.toString());
        System.out.println("结束阻塞！");
    }

    // unpark的效果是不累加
    public static void testMultipleUnparkBeforePark() {
        Thread t1 = new Thread(() -> {
            sleepInSecond(3);
            System.out.println(Thread.currentThread().getName() + ": Parking for the 1st time.");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + ": Parking for the 2nd time.");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + ": Parking for the 3rd time.");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + ": After executing parking.");
        }, "T1");

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": Unpark T1 3 times.");
            LockSupport.unpark(t1);
            LockSupport.unpark(t1);
            LockSupport.unpark(t1);

            sleepInSecond(5);

            System.out.println(Thread.currentThread().getName() + ": Unpark T1 again.");
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + " After unpark T1.");
        }, "T2");

        t1.start();
        sleepInSecond(1);
        t2.start();
    }

    private static void sleepInSecond(int sec) {
        try {
            System.out.println(Thread.currentThread().getName() + " sleep " + sec + "s.");
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " sleep over.");
    }
}
