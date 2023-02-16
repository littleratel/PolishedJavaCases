package cn.bin.serializable.externalizable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ExternalizableTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("User.info"));
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        oos.writeObject(user);
        oos.close();

        File file = new File("User.info");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        User newInstance = (User) ois.readObject();

        System.out.println(newInstance);
        ois.close();
    }
}
