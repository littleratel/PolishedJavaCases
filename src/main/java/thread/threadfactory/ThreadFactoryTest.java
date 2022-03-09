package thread.threadfactory;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;
import util.SleepUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadFactoryTest {

    @Test
    public void testThreadFactory() {
        ExecutorService pool = callbackThreadPool();
        pool.submit(() -> {
            Thread.currentThread().setUncaughtExceptionHandler((t, e) -> {
                System.out.println(t.getName() + " 捕获异常: " + e);
            });

            System.out.println("开始执行...");
            int a = 5 / 0;
            System.out.println("程序正常结束, 结果是: " + a);
        });

        SleepUtil.sleep(1);
        pool.shutdownNow();
    }

    // 自定义线程工厂
    private ExecutorService callbackThreadPool() {
        ThreadFactory factory = new ThreadFactoryBuilder()
                .setUncaughtExceptionHandler((t, e) -> {
                    // 当线程抛出异常时，后续的处理
                    System.out.println(t.getName() + " 捕获异常: " + e);
                })
                .setNameFormat("User-Thr-%d")
                .build();

        ExecutorService pool = new ThreadPoolExecutor(1,
                2, 5, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(1000), factory,
                new ThreadPoolExecutor.CallerRunsPolicy());

        return pool;
    }
}
