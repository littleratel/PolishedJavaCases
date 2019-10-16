package jvm.oom;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Dump heap memory when OOM happened
 * -XX:+HeapDumpOnOutOfMemoryError 
 * -XX:HeapDumpPath=<absolute path>
 * 
 * Run jvisualvm and load dump file
 * jvisualvm is usually in ${JAVA_HOME}/bin
 */
public class OutOfMemory extends ClassLoader {
	private static final int MEGA = 1024 * 1024;
	static String base = "string";

	/**
	 * 演示heap内存溢出 VM arg: -Xmx128m
	 */
	public static void heapMemory() {
		List<ByteBuffer> buffers = new ArrayList<>();
		while (true) {
			buffers.add(ByteBuffer.allocate(MEGA));
		}
	}

	/**
	 * 演示direct内存溢出 VM arg: -XX:MaxDirectMemorySize=32m
	 */
	public static void directMemory() {
		List<ByteBuffer> buffers = new ArrayList<>();
		while (true) {
			buffers.add(ByteBuffer.allocateDirect(MEGA));
		}
	}

	/**
	 * 区分在java6和7时, String常量存放位置变化 对于JDK6及之前版本, VM arg: -XX:MaxPermSize=8m 
	 * 对于JDK7, VM arg: -Xmx64m
	 */
	public static void stringMemory() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			String str = base + base;
			base = str;
			list.add(str.intern());
		}
	}

	/**
	 * 模拟Metaspace溢出, 需要引入ASM库 
	 * <dependency> 
	 * 	<groupId>org.ow2.asm</groupId>
	 * 	<artifactId>asm</artifactId> 
	 * 	<version>6.2.1</version> 
	 * </dependency> VM arg:
	 * -XX:-UseCompressedClassPointers -XX:MetaspaceSize=50M
	 * -XX:MaxMetaspaceSize=100m
	 */
	public static void metaspace() {
		// 类持有
		List<Class<?>> classes = new ArrayList<Class<?>>();
		// 循环1000w次生成1000w个不同的类。
		for (int i = 0; i < 10000000; ++i) {
			ClassWriter cw = new ClassWriter(0);
			// 定义一个类名称为Class{i}，它的访问域为public，父类为java.lang.Object，不实现任何接口
			cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
			// 定义构造函数<init>方法
			MethodVisitor mw = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
			// 第一个指令为加载this
			mw.visitVarInsn(Opcodes.ALOAD, 0);
			// 第二个指令为调用父类Object的构造函数
			mw.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
			// 第三条指令为return
			mw.visitInsn(Opcodes.RETURN);
			mw.visitMaxs(1, 1);
			mw.visitEnd();

			OutOfMemory test = new OutOfMemory();
			byte[] code = cw.toByteArray();
			// 定义类
			Class<?> exampleClass = test.defineClass("Class" + i, code, 0, code.length);
			classes.add(exampleClass);
		}
	}

	/**
	 * 演示stack溢出(死循环） VM arg: -Xss128
	 */
	public static void methodStack() {
		methodStack();
	}

	/**
	 * 入口方法 方法名为参数，方便用于配合vm参数演示 各项功能 如想输出GC的日志，可以加入
	 * VM arg: 
	 * -XX:+PrintGCDetails
	 * -XX:+PrintGCDateStamps
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Use: OutOfMemory <method>");
			return;
		}
		try {
			Method method = OutOfMemory.class.getDeclaredMethod(args[0]);
			method.invoke(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
