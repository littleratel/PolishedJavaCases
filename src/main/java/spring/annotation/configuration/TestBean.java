package spring.annotation.configuration;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
//@Component
public class TestBean {

    private String username;
    private String url;
    private String password;

    public void hello() {
        System.out.println("Welcome Bin.");
    }

    public String toString() {
        return "Url:" + this.url + ", username:" + this.username +", password:" + this.password;
    }

    public void init() {
        System.out.println("Init TestBean...");
        this.url = "https://www.baidu.com/";
        this.username = "user";
        this.password = "pwd";
    }

    public void destroy() {
        System.out.println("Destroy TestBean...");
    }
}