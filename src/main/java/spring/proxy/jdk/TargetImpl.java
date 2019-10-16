package spring.proxy.jdk;

//委托类
public class TargetImpl implements Target {

	private String clazz = this.getClass().getSimpleName();

	@Override
	public void create() {
		System.out.println(clazz + ".create() is called.");
	}

	@Override
	public void query(String str) {
		System.out.println(clazz + ".query() and the input arg is " + str);
	}

}
