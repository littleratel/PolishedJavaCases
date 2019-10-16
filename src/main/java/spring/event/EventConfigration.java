package spring.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfigration {

    @Bean
    public CmdService cmdService() {
        return new CmdService();
    }

    @Bean
    public CmdListener cmdListener() {
        return new CmdListener();
    }
}
