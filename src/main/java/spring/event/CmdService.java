package spring.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * ApplicationContextAware，能够获取spring的上下文环境
 */
public class CmdService implements ApplicationContextAware {

    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    SayEvent say = new SayEvent();
    public void sayCmd(String cmd) {
        say.setSource(cmd);
        ctx.publishEvent(say);  // 发布事件
    }

    public void doCmd(String cmd) {
        SayEvent evt = new SayEvent(cmd);
        ctx.publishEvent(evt);
    }
}