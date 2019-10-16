package spring.annotation.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);

        TestBean tb = (TestBean) context.getBean("testBean");
        tb.hello();
        System.out.println(tb.toString());
        System.out.println(tb);

//        TestBean tb2 = (TestBean) context.getBean("testBean");
//        tb2.hello();
//        System.out.println(tb2);
    }

    public static void testImport() {

        ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);

        TestBean tb = (TestBean) context.getBean("testBean");
        tb.hello();
        System.out.println(tb);
    }
}
