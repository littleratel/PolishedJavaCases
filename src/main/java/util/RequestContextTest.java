package util;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RequestContextTest {
    //    private static ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(1));
    private static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
//        String requestId = "req-ttl_main";
//        String userId = "CIDC-U-main";
//        String customerId = "CIDC-A-main";
//        initUserDetail(requestId, userId, customerId);

//        print("初始化");
        Thread t1 = new Thread(() -> {
            print("");
            executorService.submit(RequestContextTest::queryShop);
        });
        t1.start();
//        sleep(500);

//        print("");
        Thread t2 = new Thread(() -> {
            print("");
            executorService.submit(RequestContextTest::queryItem);
        });
        t2.start();
        sleep(500);

        System.out.println("子线程执行:=====================>");
        executorService.submit(RequestContextTest::queryCoupon);
        sleep(500);
        print("");
    }

    private static void print(String msg) {
        System.out.println(String.format("%s : %s =========>", Thread.currentThread().getName(), msg));
        System.out.println(String.format("%s : UserId:%s", Thread.currentThread().getName(), RequestContext.getCurrentContextUserId()));
        System.out.println(String.format("%s : RequestId:%s", Thread.currentThread().getName(), RequestContext.getCurrentContextRequestId()));
        System.out.println(String.format("%s : CustomerId:%s", Thread.currentThread().getName(), RequestContext.getCurrentContextCustomerId()));
    }

    private static void initUserDetail(String requestId, String userId, String customerId) {
        String customerType = "政企集团客户";

        UserDetailResp userDetail = new UserDetailResp();
        userDetail.setUserId(userId);
        userDetail.setCustomerId(customerId);
        userDetail.setCustomerType(customerType);
        RequestContext.getCurrentContext().setUserDetail(userDetail);

        RequestContext.getCurrentContext().setRequestId(requestId);
    }

    private static void queryShop() {
        print("执行前");
        String requestId = "req-test_ttl_11111";
        String userId = "CIDC-U-11111";
        String customerId = "CIDC-A-11111";
        initUserDetail(requestId, userId, customerId);
        print("执行后");
    }

    private static void queryItem() {
        print("执行前");
        String requestId = "req-test_ttl_22222";
        String userId = "CIDC-U-22222";
        String customerId = "CIDC-A-22222";
        initUserDetail(requestId, userId, customerId);

        print("执行后");
    }

    private static void queryCoupon() {
        print("执行前");
        String requestId = "req-test_ttl_33333";
        String userId = "CIDC-U-33333";
        String customerId = "CIDC-A-33333";
        initUserDetail(requestId, userId, customerId);
        print("执行后");
    }

    private static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
