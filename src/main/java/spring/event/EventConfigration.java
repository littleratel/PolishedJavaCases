package spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfigration {

    @Bean
    public EventService eventService() {
        return new EventService();
    }

    @Bean
    public ApplicationListener eventListener() {
        return new EventListener();
    }

    @Bean
    public ApplicationListener eventListenerB() {
        return new EventListener_SayEvent();
    }
}
