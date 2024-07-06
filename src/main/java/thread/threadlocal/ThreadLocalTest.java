package thread.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import util.RequestContext;
import util.SleepUtil;
import util.UserDetailResp;

import java.util.Random;

public class ThreadLocalTest {

    public final static TransmittableThreadLocal<RequestContext> tl = new TransmittableThreadLocal<RequestContext>() {
        protected RequestContext initialValue() {
            Random random = new Random();
            int item = random.nextInt(10000);

            String requestId = "req-test_ttl_" + item;
            String userId = "CIDC-U-" + item;
            String customerId = "CIDC-A-" + item;
            String customerType = "政企集团客户";
            UserDetailResp userDetail = new UserDetailResp();
            userDetail.setUserId(userId);
            userDetail.setCustomerId(customerId);
            userDetail.setCustomerType(customerType);

            RequestContext context = new RequestContext();
            context.setRequestId(requestId);
            context.setUserDetail(userDetail);

            return context;
        }
    };

    /**
     * Output:
     * Thread-0 Set 0
     * Thread-5 Set 5
     * Thread-3 Set 3
     * Thread-1 Set 1
     * Thread-4 Set 4
     * Thread-2 Set 2
     */
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            int index = i;
            Thread t = new Thread(() -> {
                try {
                    String tName = Thread.currentThread().getName();
                    System.out.println(tl.get());
//                    tl.set(tName + " Set " + index);
                    SleepUtil.sleepInMillis((int) (Math.random() * 1000));
                    System.out.println(tl.get());
                } finally {
                    tl.remove();
                }
            });

            t.start();
        }

        while (Thread.activeCount() > 2) ;

        System.out.println("End Test.");
    }
}
