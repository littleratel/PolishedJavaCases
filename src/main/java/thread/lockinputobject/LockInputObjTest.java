package thread.lockinputobject;

import org.junit.Test;

public class LockInputObjTest {
    @Test
    public void InputObjAsLockerTest() {
        //t1与t2争夺同一个锁，main与t1或t2不会争夺锁
        LockObj mainThreadObj = new LockObj();
        LockObj obj = new LockObj();

        Thread t1 = new Thread(() -> {
            print(obj);
        }, "TN-1");
        Thread t2 = new Thread(() -> {
            print(obj);
        }, "TN-2");

        t1.start();
        t2.start();
        print(mainThreadObj);
    }

    private void print(final LockObj obj) {
        if (obj == null) {
            System.out.println("LockObj obj is NULL!");
            return;
        }

        synchronized (obj) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName);
            String res = obj.say();
            System.out.println(res);
        }
    }
}
