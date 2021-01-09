package spring.proxy.jdk;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

import com.google.common.reflect.Reflection;

public class Test {

    public static void main(String[] argv) throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        // 将生成的代理类写入硬盘
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

         TestJdkProxy1();
//		 TestJdkProxy2();
//		TestJdkProxy3();
    }

    private static void TestJdkProxy1() throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Target target = new TargetImpl();

        Class<?> targetServiceClazz = Proxy.getProxyClass(Target.class.getClassLoader(), Target.class);
        Constructor<?> constructor = targetServiceClazz.getConstructor(InvocationHandler.class);
        Target targetObject = (Target) constructor.newInstance(new CheckHandler(target));

        targetObject.create();
        targetObject.query("hello, TestJdkProxy1");
    }

    private static void TestJdkProxy2() {
        Target target = new TargetImpl();

        CheckHandler checkHandler = new CheckHandler(target);
        Target targetProxy = (Target) checkHandler.getObjectProxy();
        System.out.println("=====> " + targetProxy.getClass().getName());

        // 加了第二层嵌套
        LogHandler logHandler = new LogHandler(targetProxy);
        targetProxy = (Target) logHandler.getObjectProxy();
        System.out.println("=====> " + targetProxy.getClass().getName());

        targetProxy.query("Hello, TestJdkProxy2");
    }

    private static void TestJdkProxy3() {
        Target target = new TargetImpl();
        InvocationHandler checkHandler = new CheckHandler(target);

        Target targetProxy = Reflection.newProxy(Target.class, checkHandler);
        targetProxy.query("Hello, TestJdkProxy3");
    }

}
