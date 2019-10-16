package cn.bin.classloader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * 自定义网络类加载器
 * 主要用于读取通过网络传递的class文件（在这里我们省略class文件的解密过程），并将其转换成字节流生成对应的class对象.
 * */
public class NetClassLoader extends ClassLoader {

	private String url;// class文件的URL

	public NetClassLoader(String url) {
		this.url = url;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] classData = getClassDataFromNet(name);
		if (classData == null) {
			throw new ClassNotFoundException();
		} else {
			return defineClass(name, classData, 0, classData.length);
		}
	}

	/**
	 * 从网络获取class文件
	 * 
	 * @param className
	 * @return
	 */
	private byte[] getClassDataFromNet(String className) {
		String path = classNameToPath(className);
		try {
			URL url = new URL(path);
			InputStream ins = url.openStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int bufferSize = 4096;
			byte[] buffer = new byte[bufferSize];
			int bytesNumRead = 0;
			// 读取类文件的字节
			while ((bytesNumRead = ins.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesNumRead);
			}
			// 这里省略解密的过程.......
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String classNameToPath(String className) {
		// 得到类文件的URL
		return url + "/" + className.replace('.', '/') + ".class";
	}

}
