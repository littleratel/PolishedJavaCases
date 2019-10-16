package kit3rd.reflections;

import java.lang.reflect.Field;

public class FieldReflection {

	public static void main(String[] args) {
		try {
			Field test = Father.class.getField("value");
			int val1 = (int) test.get(new Father());
			System.out.println(val1);// 3
			int val2 = (int) test.get(new Father().set(111));
			System.out.println(val2);// 111
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static class Father {
		public int value = 3;

		public Father set(int v) {
			this.value = v;
			return this;
		}
	}
}
