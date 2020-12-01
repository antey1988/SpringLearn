package annotaspectj.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class Conf_AppDemo {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Conf_AppConfig.class);
        Conf_NewDocumentarist documentarist = ctx.getBean("documentarist", Conf_NewDocumentarist.class);
        documentarist.execute();
        ctx.close();
    }
}
