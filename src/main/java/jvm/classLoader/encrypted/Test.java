package jvm.classLoader.encrypted;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

public class Test {

	public static String path = "C:\\tmp\\class\\";

	public static void main(String[] args) throws Exception {

		String className = "classloader.en.Person";
		String decryptedClazzName = className + ".en";

		// 1 加密
		encrypt(className);

		// 2 正常加载
		String path = "file:C:\\tmp\\class\\";
		URL url = new URL(path);
		URLClassLoader loader = new URLClassLoader(new URL[] { url }, null);
		Class<?> aClass = loader.loadClass("classloader.en.Person");
		System.out.println(aClass);
		String filePath = "C:\\WORK\\workspace\\classloader\\ClassLoader_confilict\\target\\classes\\classloader\\en\\ClassLoaderAttach.class";

	}

	// 加载
	private void decryptAndLoading(String className) {
		Object d = null;
		try {
			Class<?> clazz = new ClassLoaderDemo(path).loadClass(className);
			d = clazz.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println(d);
	}

	// 加密操作
	public static void encrypt(String className) throws IOException {
		String srcPath = path + className.replace(".", "\\") + ".class";
		String destFilePath = srcPath + ".en";
		FileInputStream fin = new FileInputStream(srcPath);
		FileOutputStream fout = new FileOutputStream(destFilePath);

		Unit.cypher(fin, fout);

		fin.close();
		fout.close();
		System.out.println("decrypt finished!");
	}

}
