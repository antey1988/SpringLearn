package ch3;

import ch2.decoupled.MessageProvider;
import ch2.decoupled.MessageRenderer;
import org.springframework.context.support.GenericXmlApplicationContext;

public class DeclareSpringComponents {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//        ctx.load("/spring/app-context-xml.xml");
        ctx.load("/spring/app-context-annotation.xml");
        ctx.refresh();
//        MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);
        MessageProvider mp = ctx.getBean("provider1", MessageProvider.class);
        System.out.println(mp.getMessage());
        ctx.close();
    }
}
