package cn.bin.serializable.rx;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class DeserializeTest {

	public static void main(String[] args) {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("UserInfo.out"));
			UserInfo userRx = (UserInfo) in.readObject();
			System.out.println(userRx.toString());
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
