package annotaspectj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("annotaspectj")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Conf_AppConfig {
    @Bean
    public Conf_GrammyGuitarist guitarist() {
        return new Conf_GrammyGuitarist();
    }

    @Bean
    public Conf_NewDocumentarist documentarist() {
        Conf_NewDocumentarist newDocumentarist = new Conf_NewDocumentarist();
        newDocumentarist.setGuitarist(guitarist());
        return newDocumentarist;
    }

}
