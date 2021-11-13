package spring.aop.aspect;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest {

    @Test
    public void main() {
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(AspectJConfig.class);
        MathCalculator tor = ioc.getBean(MathCalculator.class);
        System.out.println("Get Res: " + tor.add(1, 1));
        ioc.close();
    }

}