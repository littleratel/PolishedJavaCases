package spring.aop.proxyfactory;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AroundAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("Before Around.");
        Object res = methodInvocation.proceed();
        System.out.println("Before Around.");
        return res;
    }
}
