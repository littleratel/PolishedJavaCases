package spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * result: 5 2 Enter OldCodeImpl.print() 6 4 3
 */
@Component("Aspectjpps")
@Aspect
public class Aspectjpps {

//	 @Pointcut("execution(* spring.aop.TargetImpl.* (..))")
	@Pointcut("within(spring.aop.TargetImpl)") // 如果是 within(spring.aop.Target) 将不会执行代理
	public void performance() {
		System.out.println("performance(), CurrentTime = " + System.currentTimeMillis());
	};

	@Before("performance()")
	public void before() {
		System.out.println(2);
	};

	@After("performance()")
	public void after() {
		System.out.println(4);
	};

	@AfterReturning("performance()")
	public void afterReturn() {
		System.out.println(3);
	};

	@Around("performance()")
	public Object around(ProceedingJoinPoint joinpoint) {
		Object obj = null;
		try {
			System.out.println(5);
			obj = joinpoint.proceed();
			System.out.println(6);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return obj;
	};
}
