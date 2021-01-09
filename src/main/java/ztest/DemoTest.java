package ztest;

import org.junit.Test;

public class DemoTest {

    @Test
    public void testJvmMemortData() {
        print(null);
        System.out.println("<======================>");
        print("123");
    }

    private void print(final String... arr) {
        if (arr == null) {
            System.out.println("Input arr is null!");
        } else {
            System.out.println("arr.length = " + arr.length);
        }
    }
}
