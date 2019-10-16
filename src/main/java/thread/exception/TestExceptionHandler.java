package thread.exception;

public class TestExceptionHandler {

	public static void main(String[] args) {
		
		Thread.currentThread().setUncaughtExceptionHandler(new MyExceptionHandler());
		
		int a = 5 / 0;
		System.out.println("程序正常结束,结果是： " + a);
	}

	static class MyExceptionHandler implements Thread.UncaughtExceptionHandler {

		@Override
		public void uncaughtException(Thread t, Throwable e) {
			// TODO Auto-generated method stub
			System.out.println(t.getName() + " 出现了异常： " + e);
		}
	}
}
