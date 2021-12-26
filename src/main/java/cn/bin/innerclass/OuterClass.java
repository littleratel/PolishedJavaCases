package cn.bin.innerclass;

import org.junit.Test;

//
public class OuterClass {
    void fun() {
        System.out.println("Called outerClass.fun().");
    }

    public class Inner {
        // 生成对外部类的引用
        public OuterClass outer() {
            return OuterClass.this;
        }
    }

    //静态成员类
    public static class StaticInner {
        public void print() {
            System.out.println("This is a inner class. (NOT static)");
        }
    }
}

class InnerClassBean {
    @Test
    public void test() {
        OuterClass.Inner inn = new OuterClass().new Inner();
        inn.outer().fun();

        OuterClass.StaticInner staticInner = new OuterClass.StaticInner();
        staticInner.print();
    }
}
