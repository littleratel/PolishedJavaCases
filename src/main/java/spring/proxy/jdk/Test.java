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

		// TestJdkProxy1();
		// TestJdkProxy2();
		TestJdkProxy3();
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

		InvocationHandler checkHandler = new CheckHandler(target);
		/**
		 * ClassLoader loader,
		 * 定义了由哪个ClassLoader来加载该代理对象,这里使用handler的ClassLoader来加载下面的代理对象 Class<?>[]
		 * interfaces, 这里为代理对象提供的接口是真实对象所实行的接口,表示我要代理的是该真实对象,这样就能调用这组接口中的方法了
		 * InvocationHandler h,
		 * 一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上
		 */
		Target targetProxy = (Target) Proxy.newProxyInstance(checkHandler.getClass().getClassLoader(),
				target.getClass().getInterfaces(), checkHandler);
		System.out.println("=====> " + targetProxy.getClass().getName());

		// 加了第二层嵌套
		InvocationHandler logHandler = new LogHandler(targetProxy);
		targetProxy = (Target) Proxy.newProxyInstance(logHandler.getClass().getClassLoader(),
				target.getClass().getInterfaces(), logHandler);
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
