package db.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:redis\\spring-redis.xml");
	private static RedisCacheManager redisClient = (RedisCacheManager) context.getBean("redisCacheManager");
	
	public static void main(String[] args) {
		testSet();
	}
	
	private static void testSet() {
		redisClient.set("school", "nyist");
	    String value = (String) redisClient.get("school");
	    System.out.println(value);
	}
}
