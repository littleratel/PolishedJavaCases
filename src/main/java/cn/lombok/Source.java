package cn.lombok;

import cn.lombok.annotation.MyGetter;
import lombok.Getter;
import lombok.Setter;

@MyGetter
public class Source {

    private String name;

    public Source(String name) {
        this.name = name;
    }

//    @Setter
//    @Getter
//    private String host;
}
