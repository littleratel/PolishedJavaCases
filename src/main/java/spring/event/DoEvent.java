package spring.event;

import org.springframework.context.ApplicationEvent;

public class DoEvent extends ApplicationEvent {

    public DoEvent(Object source) {
        super(source);
    }
}