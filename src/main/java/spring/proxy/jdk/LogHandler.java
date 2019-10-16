package spring.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogHandler implements InvocationHandler {

	private Object target;

	public LogHandler(Object target) {
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		System.out.println(this.getClass().getSimpleName()+" print Log before call "+method.getName());		
		Object result = method.invoke(target, args);		
		System.out.println(this.getClass().getSimpleName()+" print Log after call "+method.getName());	
		return result;
	}

}
