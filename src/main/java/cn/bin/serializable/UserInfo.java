package cn.bin.serializable;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private transient String psw;
    private String waihao = "default";

    public UserInfo(String name, String psw) {
        this.name = name;
        this.psw = psw;
    }

    public String toString() {
        return "name=" + name + ", psw=" + psw;
    }
}