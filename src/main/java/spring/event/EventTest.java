package spring.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * jdk事件实现是基于观察者模式，而spring事件又是在jdk事件的基础上进行了拓展。
 * ApplicationContext事件机制是观察者设计模式的实现，通过ApplicationEvent类和ApplicationListener接口，可以实现ApplicationContext事件处理。
 * 如果容器中有一个ApplicationListener Bean，每当ApplicationContext发布ApplicationEvent时，ApplicationListener Bean将自动被触发。
 * <br>
 * 两个重要成员:
 * ApplicationEvent：容器事件，必须由ApplicationContext发布；
 * ApplicationListener：监听器，可由容器中的任何监听器Bean担任。
 */
public class EventTest {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(EventConfigration.class);
        EventService cmd = (EventService) context.getBean("eventService");
        cmd.sayCmd("Say hello word!");
//        cmd.sayCmd("Say say say u love me!");
//        cmd.doCmd("Do this event now!");
    }
}