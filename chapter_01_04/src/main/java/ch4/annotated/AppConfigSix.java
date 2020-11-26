package ch4.annotated;

import ch2.decoupled.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigSix {
    @Bean
    public MessageProvider provider() {
        return new ConfigurableMessageProvider("Love on the weekenD");
    }

}
