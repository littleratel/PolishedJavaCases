package cn.bin.generic;

import lombok.Getter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试泛型擦除
 * */
public class GenericEraseTest {

    public static void main(String[] args) {

    }

    private void test1() {
        Erasure<String> erasure = new Erasure<>("hello");
        Class clazz = erasure.getClass();
        System.out.println("Erasure: " + clazz.getName());

        //
        Field[] fs = clazz.getDeclaredFields();
        for (Field f : fs) {
            System.out.println("Field: [" + f.getName() + "] type: " + f.getType().getName());
        }

        //
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println("Method:" + m.toString());
        }
    }

    private void test2() {
        List<Integer> ls = new ArrayList<>();
        ls.add(23);
//        ls.add("123");
        try {
            Method method = ls.getClass().getDeclaredMethod("add", Object.class);
            method.invoke(ls, "test");
            method.invoke(ls, 42.9f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Object o : ls) {
            System.out.println(o);
        }
    }

}
