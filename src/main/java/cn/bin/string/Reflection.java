package cn.bin.string;

import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class Reflection {
    @SneakyThrows
    public static void main(String[] args) {
        String str = "abc";

        Class clazz = str.getClass();
        Field field = clazz.getDeclaredField("value");
        field.setAccessible(true);

        char[] arr = (char[]) field.get(str);
        arr[0] = 'd';
        arr[1] = 'e';
        arr[2] = 'f';

        System.out.println("abc".equals("def")); // true
        System.out.println(str); // def
    }
}
