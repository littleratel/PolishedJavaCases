package cn;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import designpattern.singleton.Wife;

public class ReflexTest {
	public static void main(String[] args) {
		Wife w = Wife.getWife();
		//
		String[] tmp = w.getClass().getName().split("\\.");
		String calzzName = tmp[tmp.length - 1];
		System.out.println(calzzName);

		Method methods = null;
		try {
			methods = w.getClass().getMethod("getWifeAA");
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println(e.getMessage());
		}
		try {
			System.out.println(methods.invoke(w));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}
}
