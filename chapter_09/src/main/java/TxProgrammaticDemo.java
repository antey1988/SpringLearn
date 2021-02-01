import config.DataJpaConfig;
import config.ServicesConfig;
import config.ServicesConfigTransTemp;
import entities.Singer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import services.SingerService;

import java.util.List;

public class TxProgrammaticDemo {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(DataJpaConfig.class, ServicesConfigTransTemp.class);
        SingerService singerService = ctx.getBean("singerServiceTransTemp",SingerService.class);

        System.out.println("Singer count: " + singerService.countAll());
        ctx.close();
    }
}
