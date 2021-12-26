package spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class EventListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
//        System.out.println("EventListener_A: get Event >>> " + event);

        if (event instanceof SayEvent) {
            String subject = (String) event.getSource();
            System.out.println("EventListener: get SayEvent: [" + subject + "], will say it!");
        } else if (event instanceof DoEvent) {
            String subject = (String) event.getSource();
            System.out.println("EventListener: get DoEvent: [" + subject + "], will it!");
        }
    }
}