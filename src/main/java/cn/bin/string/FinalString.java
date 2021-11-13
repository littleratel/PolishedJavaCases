package cn.bin.string;

import org.junit.Test;

public class FinalString {
    @Test
    public void testStringConstantPool() {
        String a = "xiaomeng2";
        final String b = "xiaomeng";
        String d = "xiaomeng";
        String c = b + 2;
        String e = d + 2;
        System.out.println((a == c)); // true
        System.out.println((a == e)); // false
    }

}