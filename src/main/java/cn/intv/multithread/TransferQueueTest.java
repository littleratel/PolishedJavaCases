package cn.intv.multithread;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class TransferQueueTest {
    private static TransferQueue<Integer> queue = new LinkedTransferQueue<>();

    public static void main(String[] args) {
        char[] chars = new char[]{'A', 'B', 'C'};
        int[] ints = new int[]{1, 2, 3, 4, 5, 6, 7};

        Thread t1 = new Thread(() -> {
            int index = 0;
            for (char c : chars) {
                try {
                    System.out.println(Thread.currentThread().getName() + ": " + c);
                    queue.put(ints[index++]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < ints.length; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ": " + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        t2.start();
        t1.start();
    }
}
