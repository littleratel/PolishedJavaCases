package cn.intv.multithread;

public class ThreadsAlternationPrint3 {

    static volatile int num = 1;
    static volatile int flag = 1;

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            while (num < 30) {
                if (flag == 1) {
                    System.out.println(Thread.currentThread().getName() + " : " + num++);
                    flag = 2;
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (num < 30) {
                if (flag == 2) {
                    System.out.println(Thread.currentThread().getName() + " : " + num++);
                    flag = 3;
                }
            }
        });

        Thread t3 = new Thread(() -> {
            while (num < 30) {
                if (flag == 3) {
                    System.out.println(Thread.currentThread().getName() + " : " + num++);
                    flag = 1;
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
