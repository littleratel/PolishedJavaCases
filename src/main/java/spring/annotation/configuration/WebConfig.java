package spring.annotation.configuration;

//import spring.annotation.configuration.TestConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
//@ImportResource("classpath:applicationContext-configuration.xml")
@Import(TestConfiguration.class)
public class WebConfig {
}