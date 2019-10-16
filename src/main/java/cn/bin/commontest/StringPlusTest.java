package cn.bin.commontest;

public class StringPlusTest {

	/**
	 * 字符串“+”操作, 其实是通过StingBuilder的字符串拼接操作实现的。
	 */
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		String result = "key" + ",";
		result += "value" + ",";
		result += "101";
		System.out.println(result);
	}
}
