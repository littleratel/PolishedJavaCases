package jvm.classLoader.encrypted;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

public class ClassLoaderDemo extends ClassLoader {
	private String classDir;

	public ClassLoaderDemo(String classDir) {
		this.classDir = classDir;
	}

	@Override
	protected Class<?> findClass(String classFile) throws ClassNotFoundException {
		String classFilePath = classDir + classFile.replace(".", "\\") + ".class";
		try {
			FileInputStream fin = new FileInputStream(classFilePath);
			ByteArrayOutputStream baout = new ByteArrayOutputStream();
			
			// 解密
			Unit.cypher(fin, baout);
			fin.close();
			
			// 转为字节数组
			byte[] byteArray = baout.toByteArray();
			return defineClass(null, byteArray, 0, byteArray.length);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
