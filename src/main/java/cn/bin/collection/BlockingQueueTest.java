package cn.bin.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueTest {

    public static void main(String[] args) {
        testArrayBlockingQueue();
    }

    private static void testArrayBlockingQueue() {
        // lock = new ReentrantLock(fair);
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue(5, false);

        // 在 offer 的基础上，如果无法添加抛出异常 IllegalStateException("Queue full");
        q.add("1");

        // will be blocked
        try {
            q.put("2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //如果可以容纳, 则返回true, 否则返回false.
        q.offer("3");

        q.poll();
        try {
            q.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        q.add("4");
        q.add("5");
        q.add("6");

        //
        q.remove("4");
    }

}