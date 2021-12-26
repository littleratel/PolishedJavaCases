package spring.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class CheckHandler implements InvocationHandler {

    private Object target;

    public CheckHandler(Object target) {
        this.target = target;
    }

    /**
     * ClassLoader loader,
     * 定义了由哪个ClassLoader来加载该代理对象,这里使用handler的ClassLoader来加载下面的代理对象 Class<?>[]
     * interfaces, 这里为代理对象提供的接口是真实对象所实行的接口,表示我要代理的是该真实对象,这样就能调用这组接口中的方法了
     * InvocationHandler h,
     * 一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上
     */
    public Object getObjectProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    /**
     * 通过实现InvocationHandler接口来自定义自己的InvocationHandler;
     *
     * @param proxy  代理对象
     * @param method 被调用的方法
     * @param args   被调用方法的参数
     * @return 调用方法的返回结果
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }

        String methodName = method.getName();

        System.out.println("pre-check before method: " + methodName);

        Object result = method.invoke(target, args);

        System.out.println("post-check after method: " + methodName);

        return result;
    }

}
