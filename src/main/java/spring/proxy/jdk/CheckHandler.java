package spring.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class CheckHandler implements InvocationHandler {

	private Object target;
	
	public CheckHandler(Object target) {
		this.target = target;
	}

	/**
	 * 通过实现InvocationHandler接口来自定义自己的InvocationHandler;
	 * 
	 * @param proxy 代理对象
	 * @param method 被调用的方法
	 * @param args 被调用方法的参数
	 * 
	 * @return 调用方法的返回结果
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		System.out.println(proxy.getClass().getName());	
		
		System.out.println("pre-check before method!");
		
		Object result = method.invoke(target, args);
		
		System.out.println("post-check after method!");		
				
		return result;
	}

}
