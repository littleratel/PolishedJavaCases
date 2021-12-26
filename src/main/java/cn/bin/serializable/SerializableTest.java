package cn.bin.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableTest {

    public static void main(String[] args) {
//        serializableTest();
        deserializeTest();
    }

    private static void serializableTest() {
        UserInfo user = new UserInfo("dog", "123456");
        System.out.println(user);
        try {
            // 序列化，被设置为transient的属性没有被序列化
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("UserInfo.out"));
            o.writeObject(user);
            o.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deserializeTest() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("UserInfo.out"));
            UserInfo userRx = (UserInfo) in.readObject();
            System.out.println(userRx.toString());
            System.out.println(userRx.getWaihao());
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
