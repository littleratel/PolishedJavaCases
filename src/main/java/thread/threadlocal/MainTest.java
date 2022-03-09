package thread.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainTest {
    private static final TransmittableThreadLocal<String> threadLocal = new TransmittableThreadLocal<>();
    private static ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(2));

    public static void main(String[] args) {
        threadLocal.set("main-->");
        executorService.submit(MainTest::queryShop);
        executorService.submit(MainTest::queryItem);
        executorService.submit(MainTest::queryCoupon);
    }

    /**
     * 查询店铺信息
     */
    private static void queryShop() {
        threadLocal.set(threadLocal.get() + "queryShop");
        record();
    }

    /**
     * 查询商品
     */
    private static void queryItem() {
        threadLocal.set(threadLocal.get() + "queryItem");
        record();
    }

    /**
     * 查询优惠券
     */
    private static void queryCoupon() {
        threadLocal.set(threadLocal.get() + "queryCoupon");
        record();
    }

    /**
     * 记录日志
     */
    private static void record() {
        threadLocal.set(threadLocal.get() + "-->record");
        System.out.println(Thread.currentThread().getName() + " method call chain[ " + threadLocal.get() + " ]");
    }
}
