package cn.object;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

/**
 * 查看对象内部信息： ClassLayout.parseInstance(obj).toPrintable()
 * 查看对象外部信息：包括引用的对象： GraphLayout.parseInstance(obj).toPrintable()
 * 查看对象占用空间总大小： GraphLayout.parseInstance(obj).totalSize()
 */
public class ObjectDetils {
    public static void main(String[] args) {
//        Node[] node = new Node[]{};
        Node node = new Node();
        System.out.println(Integer.toHexString(node.hashCode()));
        String str = ClassLayout.parseInstance(node).toPrintable();
        System.out.println(str);
    }

    private static class Node {
        boolean out;
    }
}