package annotaspectj.annot;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Annot_AspectJAnnotationDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:annotaspectj/app-contex-aspectj-01.xml");
        ctx.refresh();

        Annot_NewDocumentarist documentarist = ctx.getBean("documentarist", Annot_NewDocumentarist.class);
        documentarist.execute();
    }
}
