package cn.bin.atomic;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderTest {

    public static void main(String[] args) {
        LongAdder adder = new LongAdder();
        adder.add(1);

        System.out.println(adder.intValue());
    }

}
