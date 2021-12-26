package cn.intv.multithread;

import java.util.concurrent.locks.LockSupport;

public class ParkAndUnpark {

    private Thread t1 = null, t2 = null;

    public void test() {
        char[] chars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[] ints = new int[]{1, 2, 3, 4, 5, 6, 7};

        /**
         * 匿名内部类访问**方法中声明的局部变量**必须是final的
         * 匿名内部类访问**外部类局部变量**没有要求
         * */
        // Thread t1 = null, t2 = null;
        t1 = new Thread(() -> {
            for (char c : chars) {
                System.out.println(Thread.currentThread().getName() + ": " + c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "t1");

        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i : ints) {
                    LockSupport.park();
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    LockSupport.unpark(t1);
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        ParkAndUnpark test = new ParkAndUnpark();
        test.test();
    }
}
