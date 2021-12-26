package spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CalculatorAspectJ {

    @Pointcut("execution(* spring.aop.aspect.MathCalculator.div(..))")
    private void pointcut() {
    }

    @Before("pointcut()")
    public void beforeCut(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName + ", Before: 1");
    }

    @After("pointcut()")
    public void AfterCut(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName + ", After: 2");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName + ", Around - before: 3");
        Object retVal = joinPoint.proceed();
        System.out.println(methodName + ", Around - after: 4. Result: " + retVal);
        return retVal;
    }

    @AfterReturning(returning = "result", value = "pointcut()")
    public void afterReturningExecute(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName + ", AfterReturning - result: " + result);
    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "error")
    public void doAfterThrowing(Throwable error) {
        System.out.println("AfterThrowing: " + error.toString());
    }
}
