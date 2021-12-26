package spring.event;

import org.springframework.context.ApplicationListener;

public class EventListener_SayEvent implements ApplicationListener<SayEvent> {

    @Override
    public void onApplicationEvent(SayEvent event) {
        String subject = (String) event.getSource();
        System.out.println("EventListener_SayEvent: get SayEvent: [" + subject + "], will say it!");
    }
}