package carcassservices;

import aopbasics.advisorandpointcut.namepointcut.GrammyGuitarist;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ProxyFactoryDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:carcassservice/app-contex-advice.xml");
        ctx.refresh();

       Documentarist documentaristOne = ctx.getBean("documentaristOne", Documentarist.class);
        Documentarist documentaristTwo = ctx.getBean("documentaristTwo", Documentarist.class);
//        GrammyGuitarist johnMayer = ctx.getBean("proxyOne", GrammyGuitarist.class);

        System.out.println("Documentaris One >>");
//        johnMayer.sing();
        documentaristOne.execute();
        System.out.println("\nDocumentaris Two >>");
        documentaristTwo.execute();

    }
}
