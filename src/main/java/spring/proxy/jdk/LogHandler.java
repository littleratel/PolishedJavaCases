package spring.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogHandler implements InvocationHandler {

	private Object target;

	public LogHandler(Object target) {
		this.target = target;
	}

	public Object getObjectProxy() {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		if (Object.class.equals(method.getDeclaringClass())) {
			return method.invoke(this, args);
		}

		System.out.println(this.getClass().getSimpleName()+" print Log before call "+method.getName());
		Object result = method.invoke(target, args);
		System.out.println(this.getClass().getSimpleName()+" print Log after call "+method.getName());
		return result;
	}

}
