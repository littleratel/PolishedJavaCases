package spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		testAopWithAnnotation();
//		 testXml();
	}

	private static void testAopWithAnnotation() {
		// 这里不会执行AOP,调用IOC容器中管理的对应的bean时才能执行aop
		// Target bean = new TargetImpl();
		// bean.print();

		ApplicationContext ctx = new ClassPathXmlApplicationContext("aop/aop-annotation.xml");
		Target target = (Target) ctx.getBean("targetImpl");
		target.print();
	}

	private static void testXml() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:aop/aop.xml");
		Target target = (Target) ctx.getBean("targetImpl");
		target.print();
		System.out.println("======= cut-off =======");
		target.doPrint();
	}

}