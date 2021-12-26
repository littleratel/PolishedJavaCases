package spring.proxy.cglib;

public class Test {

	public static void main(String[] args) {
		CglibProxy proxy = new CglibProxy();

		// 将生成的代理类写入硬盘
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

		// 通过生成子类的方式创建代理类
		SayHello proxyImp = (SayHello) proxy.getProxy(SayHello.class);
		proxyImp.say("hello word!");
	}

}
