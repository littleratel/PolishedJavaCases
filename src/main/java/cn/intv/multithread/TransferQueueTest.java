package cn.intv.multithread;

import util.SleepUtil;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class TransferQueueTest {
    private static TransferQueue<String> queue = new LinkedTransferQueue<>();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            String[] strArr = {"A1", "B1", "C1"};
            for (String s : strArr) {
                try {
                    System.out.println(Thread.currentThread().getName() + " start to transfer: " + s);
                    queue.transfer(s);
                    System.out.println(Thread.currentThread().getName() + " return：" + s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "P1");

        Thread t2 = new Thread(() -> {
            String[] strArr = {"A2", "B2", "C2"};
            for (String s : strArr) {
                try {
                    System.out.println(Thread.currentThread().getName() + " start to transfer: " + s);
                    queue.transfer(s);
                    System.out.println(Thread.currentThread().getName() + " return：" + s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "P2");

        Thread t3 = new Thread(() -> {
            for (int i = 1; i <= 6; i++) {
                try {
                    SleepUtil.sleep(1);
                    String ans = queue.take();
                    System.out.println(Thread.currentThread().getName() + " took: " + ans);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer");

        t3.start();
        t2.start();
        t1.start();
        System.out.println();
    }
}
