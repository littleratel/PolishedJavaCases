package cn.bin.serializable.tx;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SerializableTest {

	public static void main(String[] args) {
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
}
