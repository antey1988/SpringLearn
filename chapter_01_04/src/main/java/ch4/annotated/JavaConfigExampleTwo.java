package ch4.annotated;

import ch2.decoupled.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JavaConfigExampleTwo {
    public static void main(String[] args) {
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigOne.class);
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigTwo.class);
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigThree.class);
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigFive.class);
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring/annotated/app-context-02.xml");
        MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);
        renderer.render();
    }
}
