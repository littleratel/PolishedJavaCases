package spring.bean.autowirednjectionmodel;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 验证注解 Autowired 的默认注入方式，byName or byType
 * */
public class Test {

	public static void main(String[] args){
		// 非web环境下我们通常这么来加载IOC容器，获取bean
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("beans/AutowiredTestBeans.xml");
		IUserService userService = (IUserService) app.getBean("userService");
		userService.testService();
	}
}
