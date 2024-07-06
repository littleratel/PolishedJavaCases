package util;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Optional;
import java.util.Random;

@Data
public class RequestContext {
    private static final TransmittableThreadLocal<RequestContext> REQUEST_CONTEXT_TTL = new TransmittableThreadLocal<RequestContext>() {
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

//        protected void beforeExecute() {
//            RequestContext context = this.get();
//            if (context == null) {
//                return;
//            }
//            RequestContext newContext = new RequestContext();
//            BeanUtils.copyProperties(context, newContext);
//            this.set(newContext);
//        }
    };

    private String requestId;
    private UserDetailResp userDetail;
    private String userId;
    private String manageUserId;
    private String region;

    public static RequestContext getCurrentContext() {
        return REQUEST_CONTEXT_TTL.get();
    }

    public static void remove() {
        REQUEST_CONTEXT_TTL.remove();
    }

    public static String getCurrentContextRequestId() {
        return getCurrentContext().getRequestId();
    }

    public static UserDetailResp getCurrentContextUserDetail() {
        return getCurrentContext().getUserDetail();
    }

    public static String getCurrentContextUserId() {
        return Optional.ofNullable(getCurrentContextUserDetail()).map(UserDetailResp::getUserId).orElse(null);
    }

    public static String getCurrentContextCustomerId() {
        return Optional.ofNullable(getCurrentContextUserDetail()).map(UserDetailResp::getCustomerId).orElse(null);
    }

    public static String getCurrentContextManageUserId() {
        return getCurrentContext().getManageUserId();
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setUserDetail(UserDetailResp userDetail) {
        this.userDetail = userDetail;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setManageUserId(String manageUserId) {
        this.manageUserId = manageUserId;
    }
}
