package thread.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransmittableThreadLocalTest {
    private static final TransmittableThreadLocal<String> ttl = new TransmittableThreadLocal<>();
    private static ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(1));

    public static void main(String[] args) {
        ttl.set("main-->");
        executorService.submit(TransmittableThreadLocalTest::queryShop);
        executorService.submit(TransmittableThreadLocalTest::queryItem);
        executorService.submit(TransmittableThreadLocalTest::queryCoupon);
    }

    /**
     * 查询店铺信息
     */
    private static void queryShop() {
        ttl.set(ttl.get() + "queryShop");
        record();
    }

    /**
     * 查询商品
     */
    private static void queryItem() {
        ttl.set(ttl.get() + "queryItem");
        record();
    }

    /**
     * 查询优惠券
     */
    private static void queryCoupon() {
        ttl.set(ttl.get() + "queryCoupon");
        record();
    }

    /**
     * 记录日志
     */
    private static void record() {
        ttl.set(ttl.get() + "-->" + Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + " method call chain[ " + ttl.get() + " ]");
    }
}
