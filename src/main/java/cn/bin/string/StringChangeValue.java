package cn.bin.string;

import java.lang.reflect.Field;

public class StringChangeValue {

    public static void main(String[] args) throws Exception {
        String str = "abc";
        Class clz = str.getClass();
        //需要使用getDeclaredField(), getField()只能获取公共成员字段
        Field field = clz.getDeclaredField("value");
        field.setAccessible(true); // private

        char[] ch = (char[]) field.get(str);
        ch[1] = '8';
//        char[] newChar = "This is a new string!".toCharArray();
//        field.set("value",newChar); // 不生效
        System.out.println(str);
    }

}


