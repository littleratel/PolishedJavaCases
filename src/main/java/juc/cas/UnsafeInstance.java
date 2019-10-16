package juc.cas;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * 想获取Unsafe实例，如果直接调用: <br>
 * Unsafe unsafe = Unsafe.getUnsafe(); <br>
 * 会抛:<br>
 * Exception in thread "main" java.lang.SecurityException: Unsafe
 * 
 * <pre>
 * public static Unsafe getUnsafe() {
 * 	Class cc = sun.reflect.Reflection.getCallerClass(2);
 * 	if (cc.getClassLoader() != null)
 * 		throw new SecurityException("Unsafe");
 * 	return theUnsafe;
 * }
 * </pre>
 * 
 * 也即仅BootstrapClassLoader加载的类,才能通过Unsafe.getUnsafe()获取.
 * 也即$JAVA_HOME/lib目录下jar包包含的类，如
 * <ul>
 * <li>java.util.concurrent.atomic.AtomicInteger
 * </ul>
 * 
 * 是因为有这样一段说明: <br>
 * Although the class and all methods are public, use of this class is limited
 * because only trusted code can obtain instances of it. </br>
 * 只有是java认为安全的代码才可以获取Unsafe实例。</br>
 * 
 * 通过反射绕过验证, 获得Unsafe类实例. <br>
 * Unsafe类里有一个静态的Unsafe实例，名称是"theUnsafe", 获得方法如下: <br>
 * http://hg.openjdk.java.net/jdk8u/hs-dev/jdk/file/a006fa0a9e8f/src/share/classes/sun/misc/Unsafe.java
 * 
 * <pre>
 * Field field = Unsafe.class.getDeclaredField("theUnsafe");
 * field.setAccessible(true);
 * Unsafe unsafe = (Unsafe) field.get(null);
 * </pre>
 */
@SuppressWarnings("restriction")
public class UnsafeInstance {

	public static void main(String[] args)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		Field field = Unsafe.class.getDeclaredField("theUnsafe");
		field.setAccessible(true);
		Unsafe unsafe = (Unsafe) field.get(null);

		// 获得Target实例域value
		Field valueField = Target.class.getDeclaredField("value");
		Target tar = new Target();
		System.out.println("原始value值:" + valueField.get(tar));

		// 获得实例域在class文件里的偏移量
		final long valueOffset = unsafe.objectFieldOffset(valueField);
		int curValue = unsafe.getIntVolatile(tar, valueOffset); // 从主存获取
		// unsafe.getInt(tar, valueOffset);
		System.out.println("swap(10,20)是否成功: " + unsafe.compareAndSwapInt(tar, valueOffset, curValue, curValue + 10));
		System.out.println("更新后value值为: " + valueField.get(tar));
	}

	static class Target {
		public int value = 10;
	}
}
