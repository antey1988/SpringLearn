package dao;

import config.AppConfig;
import entities.Album;
import entities.Singer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.sql.Date;
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
         Assert.assertNotNull(singerDao);
    }

    @After
    public void closeContext() {
        ctx.close();
    }

    @Test
    public void testFindAll() {
        List<Singer> singers = singerDao.findAll();
        Assert.assertEquals(singers.size(), 3);
        listSingers(singers);
    }

    @Test
    public void testFindAllWithAlbum() {
        List<Singer> singers = singerDao.findAllWithAlbum();
        Assert.assertEquals(singers.size(), 3);
        listSingersWithAlbums(singers);
    }

    @Test
    public void testFindById() {
        Singer singer = singerDao.findById(1L);
        Assert.assertNotNull(singer);
        listSingersWithAlbums(Collections.singletonList(singer));
    }

    @Test
    public void testInsert() {
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(Date.valueOf("1940-8-16"));
        Album album = new Album();
        album.setTitle("My Kings of Blues");
        album.setReleaseDate(Date.valueOf("1961-8-15"));
        singer.addAlbum(album);
        album = new Album();
        album.setTitle("A Heart Full of Blues");
        album.setReleaseDate(Date.valueOf("1962-5-24"));
        singer.addAlbum(album);

        singerDao.save(singer);
        Assert.assertNotNull(singer.getId());

        List<Singer> singers = singerDao.findAllWithAlbum();
        Assert.assertEquals(4, singers.size());
        listSingersWithAlbums(singers);
    }

    @Test
    public void testUpdate() {
        Singer singer = singerDao.findById(1L);
        Assert.assertNotNull(singer);
        Assert.assertEquals(singer.getLastName(), "Mayer");

        Album album = singer.getAlbums().stream().filter(a -> a.getTitle().equals("Battle Studies")).findFirst().get();

        singer.setFirstName("John Clayton");
        singer.removeAlbum(album);

        singerDao.save(singer);

        listSingersWithAlbums(singerDao.findAllWithAlbum());
    }

    @Test
    public void testDelete () {
        Singer singer = singerDao.findById(2L);
        Assert.assertNotNull(singer);
        singerDao.delete(singer);

        listSingersWithAlbums(singerDao.findAllWithAlbum());

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
