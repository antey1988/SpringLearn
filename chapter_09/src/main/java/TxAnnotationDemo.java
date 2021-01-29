import config.DataJpaConfig;
import config.ServicesConfig;
import entities.Singer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import services.SingerService;

import java.util.List;

public class TxAnnotationDemo {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(DataJpaConfig.class, ServicesConfig.class);
        SingerService singerService = ctx.getBean(SingerService.class);

        List<Singer> singers = singerService.findAll();
        singers.forEach(System.out::println);
        ctx.close();
    }
}
