package carcassservices;

import aopbasics.introduction.Contact;
import aopbasics.introduction.IsModifiedAdvisor;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppIntroductionConfig {
    @Bean
    public Contact guitarist() {
        Contact contact = new Contact();
        contact.setName("John Mayer");
        return contact;
    }

    @Bean
    public Advisor advisor() {
        return  new IsModifiedAdvisor();
    }

    @Bean
    public ProxyFactoryBean bean() {
        ProxyFactoryBean pf = new ProxyFactoryBean();
        pf.setTarget(guitarist());
        pf.addAdvisor(advisor());
        pf.setProxyTargetClass(true);
        return pf;
    }
}
