package thread.lock;

import java.util.concurrent.locks.LockSupport;

import com.google.common.base.Stopwatch;

public class LockSupportTest {
    public static void main(String[] args) {
//        testParkTime();
        testMultipleUnparkBeforePark();
    }

    //
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
            LockSupport.parkUntil(1000);
            System.out.println(Thread.currentThread().getName() + ": Parking for the 1st time.");
            LockSupport.park();
//            System.out.println(Thread.currentThread().getName() + ": Parking for the 2nd time.");
//            LockSupport.park();
//            System.out.println(Thread.currentThread().getName() + ": Parking for the 3rd time.");
//            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + ": Finished!");
        }, "T1");

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": Unpark T1 1st ...");
            LockSupport.unpark(t1);
//            LockSupport.parkUntil(1000);

            System.out.println(Thread.currentThread().getName() + ": Unpark T1 2nd ...");
            LockSupport.unpark(t1);
//            LockSupport.parkUntil(1000);

            System.out.println(Thread.currentThread().getName() + ": Unpark T1 1rd ...");
            LockSupport.unpark(t1);
//            LockSupport.parkUntil(1000);


            System.out.println(Thread.currentThread().getName() + ": Unpark T1 4th ...");
            LockSupport.unpark(t1);
        }, "T2");

        t1.start();
//        LockSupport.parkUntil(1000);
        t2.start();

        while (Thread.activeCount() > 1) {
            Thread.yield(); // 让出 CPU
        }
    }
}
