package thread.threadpool;

import util.SleepUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CachedThreadPool适合去处理执行时间比较小的任务
 *
 * 1.任务执行时间较长。 每次有新的请求到来的时候，由于之前的线程不是空闲状态，而且这种线程池的阻塞队列不能存储，
 * 所以需要开辟新的线程来处理这个请求，这样就会创建大量的线程。
 * 2.任务执行时间较短。在请求处理前，给之前处理任务的线程足够的时间，使得之前线程能够继续处理接下来的请求
 * 3.任务执行时间较长，使用该线程池会出现堆栈溢出异常 （设置jvm参数-Xmx16m -Xss512k） Exception in thread
 * "main" java.lang.OutOfMemoryError: Java heap space
 */
public class CachedThreadPoolTest {
    public static void main(String[] args) {

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int index = i + 1;

            // 场景2：任务处理时间较短
            SleepUtil.sleepInMillis(500);

            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    // 场景1：任务处理时间较长
                    SleepUtil.sleepInMillis(5000);
                    System.out.println(Thread.currentThread().getName() + "执行任务" + index);
                }
            });
        }

        cachedThreadPool.shutdown();
    }

}
