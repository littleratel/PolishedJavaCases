package cn.bin.regex;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class PatternTest {

    @Test
    public void testPattern() {
        Consumer c1 = str -> System.out.println("Consumer 1: " + str);
        Consumer c2 = str -> System.out.println("Consumer 2: " + str);
        Consumer c3 = str -> System.out.println("Consumer 3: " + str);
        c1.andThen(c2).andThen(c3).accept("Hello Word!");
    }
}
