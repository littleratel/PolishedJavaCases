package spring.aop.proxyfactory;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

public class BeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args1, Object o) throws Throwable {
        System.out.println("BeforeAdvice: 拦截方法: " + method.getName() + ". 参数为: " + Arrays.asList(args1));
    }
}
