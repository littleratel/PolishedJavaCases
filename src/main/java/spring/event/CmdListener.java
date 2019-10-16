package spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class CmdListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("MessageListener: get Event >>> " + event);

        if (event instanceof SayEvent) {
            String subject = (String) event.getSource();
            System.out.println("MessageListener: get SayEvent: [" + subject + "], will say it!");
        } else if (event instanceof DoEvent) {
            String subject = (String) event.getSource();
            System.out.println("MessageListener: get DoEvent: [" + subject + "], will it!");
        }
    }
}