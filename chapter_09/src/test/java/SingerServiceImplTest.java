import config.DataJpaConfig;
import config.ServicesConfig;
import entities.Singer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import services.SingerService;

import java.util.List;

public class SingerServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(SingerServiceImplTest.class);

    private GenericApplicationContext ctx;
    private SingerService singerService;

    @Before
    public void up() {
        ctx = new AnnotationConfigApplicationContext(DataJpaConfig.class, ServicesConfig.class);
        singerService = ctx.getBean(SingerService.class);
    }

    @After
    public void down() {
        ctx.close();
    }

    public void testFindAll() {
        List<Singer> singers = singerService.findAll();
        singers.forEach(s-> logger.info(s.toString()));
    }
}
