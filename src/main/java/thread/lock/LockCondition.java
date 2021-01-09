package thread.lock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCondition {

    public static void main(String[] args) {
        final TaskQueue queue = new TaskQueue();
        Thread p = new Thread(() -> {
            try {
                String pre = "Producer msg index: ";
                for (int i = 0; i < 5; i++) {
                    queue.putTask(pre + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "P");
        Thread c = new Thread(new Consumer(queue), "C1");
        Thread c2 = new Thread(new Consumer(queue), "C2");
        Thread c3 = new Thread(new Consumer(queue), "C3");

        c.start();
        p.start();
    }

    private static class TaskQueue {
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();
        private Queue<String> queue = new LinkedList<>();

        public void putTask(String s) {
            lock.lock();
            try {
                queue.add(s);
                condition.signal();    //唤醒第1个Node,通过 LockSupport.unpark(node.thread)实现
//                condition.signalAll(); //遍历链表，逐个唤醒
            } finally {
                lock.unlock();
            }
        }

        public String getTask() throws InterruptedException {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    condition.await();
                }
                return queue.remove();
            } finally {
                lock.unlock();
            }
        }
    }// end class TaskQueue

    private static class Consumer implements Runnable {
        private final TaskQueue queue;

        public Consumer(TaskQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (; ; ) {
                    queue.getTask();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
