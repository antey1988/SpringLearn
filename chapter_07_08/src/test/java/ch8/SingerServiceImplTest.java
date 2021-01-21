package ch8;

import ch8.entities.Singer;
import ch8.config.JpaConfig;
import ch8.services.SingerService;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.junit.Assert;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Collections;
import java.util.List;

public class SingerServiceImplTest {
    private static Logger logger = LoggerFactory.getLogger(SingerServiceImplTest.class);

    private GenericApplicationContext ctx;
    private SingerService singerService;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        singerService = ctx.getBean(SingerService.class);
        Assert.assertNotNull(singerService);
    }

    @After
    public void tearDown() {
        ctx.close();
    }

    private void listSingers(List<Singer> singers) {
        logger.info("----Listing singers:");
        singers.forEach(s -> logger.info(s.toString()));
    }

    private void listSingersWithAlbums(List<Singer> singers) {
        logger.info("----Listing singers with album and instruments:");
        singers.forEach(singer -> {
            logger.info(singer.toString());
            if (singer.getAlbums() != null)
                singer.getAlbums().forEach(album -> logger.info("\t" + album.toString()));
            if (singer.getInstruments() != null)
                singer.getInstruments().forEach(instrument -> logger.info("\t" + instrument.toString()));
        });
    }

    @Test
    public void testFindAll() {
        List<Singer> singers = singerService.findAll();
        Assert.assertEquals(3, singers.size());
        listSingers(singers);
    }

    @Test
    public void testFindAllWithAlbum() {
        List<Singer> singers = singerService.findAllWithAlbum();
        Assert.assertEquals(3, singers.size());
        listSingersWithAlbums(singers);
    }

    @Test
    public void testFindById() {
        Singer singer = singerService.findById(1L);
        Assert.assertNotNull(singer);
        listSingersWithAlbums(Collections.singletonList(singer));
    }
}
