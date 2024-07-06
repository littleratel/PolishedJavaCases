package util;

import lombok.Data;

import java.util.List;

@Data
public class UserDetailResp {
    private String userId;
    private String userName;
    private String customerId;
    private String customerType;
    private List<String> authorities;
}
