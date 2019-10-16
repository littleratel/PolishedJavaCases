package spring.event;

import org.springframework.context.ApplicationEvent;

public class SayEvent extends ApplicationEvent {

    public SayEvent(Object source) {
        super(source);
    }

    public SayEvent(){
        super("NULL");
    }

    public void setSource(Object source){
        this.source = source;
    }
}