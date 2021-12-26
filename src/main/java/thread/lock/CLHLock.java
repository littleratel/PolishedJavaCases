package thread.lock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class CLHLock {
    private volatile CLHNode tail;
    /**
     * 参数说明：
     * 第一个参数：被更新属性所在的类
     * 第二个参数：被更新属性的类型
     * 第三个参数：被更新属性的名称
     */
    private final AtomicReferenceFieldUpdater<CLHLock, CLHNode> UPDATER = AtomicReferenceFieldUpdater
            .newUpdater(CLHLock.class, CLHNode.class, "tail");

    public void lock(CLHNode currentThread) {
        CLHNode preNode = UPDATER.getAndSet(this, currentThread);
        if (preNode != null) {   //已有线程占用了锁，进入自旋
            while (preNode.isWaiting) {
            }
        }
    }

    public void unlock(CLHNode currentThread) {
        // 如果队列里只有当前线程，则释放对当前线程的引用（for GC）。
        if (!UPDATER.compareAndSet(this, currentThread, null)) {
            // 还有后续线程
            currentThread.isWaiting = false;// 改变状态，让后续线程结束自旋
        }
    }

    private class CLHNode {
        private volatile boolean isWaiting = true; // 默认是在等待锁
    }
}