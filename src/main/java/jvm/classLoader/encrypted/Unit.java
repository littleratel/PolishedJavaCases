package jvm.classLoader.encrypted;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class Unit {
	/**
	 * Description:加密， 将原来的1改为0,0改为1
	 */
	public static void cypher(InputStream in, OutputStream out) throws IOException {
		int b = 0;
		while ((b = in.read()) != -1) {
			out.write(b ^ 0xff);
		}
	}
}
