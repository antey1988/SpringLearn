import config.AppConfig;
import dao.SingerDao;
import dao.SingerDaoImp;
import entities.Singer;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Collections;
import java.util.List;

public class SingerDaoImpTest {
    private static Logger logger = LoggerFactory.getLogger(SingerDaoImpTest.class);

    GenericApplicationContext ctx;
    SingerDao singerDao;

    @Before
    public void initContext() {
         ctx = new AnnotationConfigApplicationContext(AppConfig.class);
         singerDao = ctx.getBean(SingerDao.class);
    }

    @Test
    public void testFindAll() {
        listSingers(singerDao.findAll());
    }

    @Test
    public void testFindAllWithAlbumAndInstrument() {
        listSingersWithAlbums(singerDao.findAllWithAlbum());
    }

    @Test
    public void testFindById() {
        listSingersWithAlbums(Collections.singletonList(singerDao.findById(1L)));
    }

    private void listSingers(List<Singer> singers) {
        logger.info("----Listing singers:");
        singers.forEach(System.out::println);
    }

    private void listSingersWithAlbums(List<Singer> singers) {
        logger.info("----Listing singers with album and instruments:");
        singers.forEach(singer -> {
            logger.info(singer.toString());
            if (singer.getAlbums() != null)
                singer.getAlbums().forEach(album -> {
                    logger.info("\t" + album.toString());
                });
            if (singer.getInstruments() != null)
                singer.getInstruments().forEach(instrument -> {
                    logger.info("\t" + instrument.toString());
                });
        });
    }
}
