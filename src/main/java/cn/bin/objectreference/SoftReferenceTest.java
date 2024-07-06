package cn.bin.objectreference;

import java.lang.ref.SoftReference;

public class SoftReferenceTest {

    public static void main(String[] args) {
        SoftReference<String> softRef = new SoftReference<>("abc");
        System.out.println(softRef.get());
    }

}
