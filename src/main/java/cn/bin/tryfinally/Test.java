package cn.bin.tryfinally;

public class Test {

	public static void main(String[] args) {
		System.out.println(testFinally());
		System.out.println(testFinally1());
	}

	@SuppressWarnings("finally")
	private static boolean testFinally() {
		try {
			return true;
		} catch (Exception e) {
			return true;
		} finally {
			return false;
		}
	}

	private static int testFinally1() {
		int a = 0;
		try {
			return a = 1;
		} catch (Exception e) {
			return a = 2;
		} finally {
			a = 3;
		}
	}

}
