package connectionpool.eric.test;

import lombok.ToString;

@ToString
public class Coli {
    private final String id;

    public Coli(String id) {
        this.id = id;
    }

    public String send(String cmd) {
        return Thread.currentThread().getName() + " send " + cmd;
    }
}
