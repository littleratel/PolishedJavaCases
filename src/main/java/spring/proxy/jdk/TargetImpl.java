package spring.proxy.jdk;

//委托类
public class TargetImpl implements Target {

	@Override
	public void create() {
		System.out.println("TargetImpl.create() is called.");
	}

	@Override
	public void query(String str) {
		System.out.println("TargetImpl.query() and the input arg is " + str);
	}

}
