package cn.bin;

public class MethodOverLoadTest {

	public static void main(String[] args) {
		Test test = new Test();
		test.print(null);
	}

	public static class Test {
		// 定义一个方法print()
		public void print(String some) {
			System.out.println("String version print");
		}

		// 重载print()方法
		public void print(Object some) {
			System.out.println("Object version print");
		}
	}
}
