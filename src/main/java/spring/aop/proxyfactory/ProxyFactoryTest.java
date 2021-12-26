package spring.aop.proxyfactory;

import org.junit.Test;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.DecoratingProxy;

import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyFactoryTest {

    @Test
    public void springAopProxyMainTest() {
        ProxyFactory proxyFactory = new ProxyFactory(new Demo());
        proxyFactory.addAdvice(new AroundAdvice());
        proxyFactory.addAdvice(new BeforeAdvice());

        DemoInterface demo = (DemoInterface) proxyFactory.getProxy();
        demo.hello("Bob");

        System.out.println("<=============================================>");
        System.out.println(proxyFactory.getTargetClass()); //class spring.aop.aopproxy.Demo
        System.out.println(proxyFactory.getTargetSource()); //SingletonTargetSource for target object [spring.aop.aopproxy.Demo@6ea12c19]
        System.out.println(Arrays.asList(proxyFactory.getProxiedInterfaces()));
        System.out.println(Arrays.asList(proxyFactory.getAdvisors()));

        // 获取类型，看看是JDK代理还是cglib的
        System.out.println("<=============================================>");
        System.out.println(demo instanceof Proxy); //true
        System.out.println(demo.getClass()); //class spring.aop.aopproxy.$Proxy4
        System.out.println(Proxy.isProxyClass(demo.getClass())); //true
        System.out.println(AopUtils.isCglibProxy(demo)); //false

        //测试Advised接口、DecoratingProxy的内容
        System.out.println("<=============================================>");
        Advised advised = (Advised) demo;
        System.out.println(Arrays.asList(advised.getProxiedInterfaces())); //[interface spring.aop.aopproxy.DemoInterface]
        System.out.println(Arrays.asList(advised.getAdvisors()));
        System.out.println(advised.isExposeProxy()); //false
        System.out.println(advised.isFrozen()); //false

        System.out.println("<=============================================>");
        DecoratingProxy decoratingProxy = (DecoratingProxy) demo;
        System.out.println(decoratingProxy.getDecoratedClass()); //class spring.aop.aopproxy.Demo

        // Object的方法 ==== 所有的Object方法都不会被AOP代理 这点需要注意
        System.out.println("<=============================================>");
        System.out.println(demo.equals(new Object())); // false
        System.out.println(demo.hashCode());

        // 其余方法都没被拦截
        System.out.println("<=============================================>");
        // 拦截方法： toString(), 参数为: []
        // spring.aop.aopproxy.Demo@6ea12c19
        System.out.println(demo.toString());
    }

    interface DemoInterface {
        void hello(String user);
    }

    class Demo implements DemoInterface {
        @Override
        public void hello(String user) {
            System.out.println("Hi, " + user);
        }
    }
}


