package cn.bin.serializable;

import lombok.Data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableTest {

    public static void main(String[] args) {
        System.out.println("开始序列化...........");
        serializableTest();

        System.out.println("开始反序列化..........");

        deserializeTest();

        System.out.println();
    }

    private static void serializableTest() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Foo2.dat"));
            Foo2 foo2 = new Foo2();
            foo2.setFieldFoo("Foo");
            foo2.setFieldFoo1("Foo1");
            foo2.setFieldFoo2("Foo2");

            oos.writeObject(foo2);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deserializeTest() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Foo2.dat"));
            Foo2 foo2 = (Foo2) ois.readObject();

            System.out.println(foo2);
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

@Data
class Foo {
    private String fieldFoo;

    public Foo() {
        System.out.println("foo...");
    }
}

@Data
class Foo1 extends Foo {
    private String fieldFoo1;

    public Foo1() {
        System.out.println("foo1...");
    }
}

@Data
class Foo2 extends Foo1 implements Serializable {
    private String fieldFoo2;

    public Foo2() {
        System.out.println("foo2...");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fieldFoo2=").append(fieldFoo2);
        sb.append(", fieldFoo1=").append(getFieldFoo1());
        sb.append(", fieldFoo=").append(getFieldFoo());

        return sb.toString();
    }
}