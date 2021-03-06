package ch2;

import ch2.decoupled.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;

public class HelloWorldSpringDI {
    public static void main(String[] args) {
//        URL url = HelloWorldSpringDI.class.getResource(".").toURI();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring/app-context.xml");
        MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);
        mr.render();
    }
}
