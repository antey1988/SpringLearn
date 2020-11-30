package carcassservices;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AopNamespaceDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//        ctx.load("classpath:carcassservice/app-contex-namespace-01.xml");
//        ctx.load("classpath:carcassservice/app-contex-namespace-02.xml");
        ctx.load("classpath:carcassservice/app-contex-namespace-03.xml");
        ctx.refresh ();
        NewDocumentarist documentarist = ctx.getBean("documentarist", NewDocumentarist.class);
        documentarist.execute();
        ctx.close();
    }
}
