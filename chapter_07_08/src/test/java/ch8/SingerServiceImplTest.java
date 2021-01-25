package ch8;

import ch8.entities.Album;
import ch8.entities.Singer;
import ch8.config.JpaConfig;
import ch8.services.SingerService;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.junit.Assert;
import org.springframework.context.support.GenericApplicationContext;

import java.sql.Date;
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

        singerService.save(singer);
        Assert.assertNotNull(singer.getId());

        List<Singer> singers = singerService.findAllWithAlbum();
        Assert.assertEquals(4, singers.size());
        listSingersWithAlbums(singers);
    }

    @Test
    public void testUpdate() {
        Singer singer = singerService.findById(1L);
        Assert.assertNotNull(singer);
        Assert.assertEquals("Mayer", singer.getLastName());

        Album album = singer.getAlbums().stream().filter(a->a.getTitle().equals("Battle Studies")).findFirst().get();
        singer.setFirstName("John Clayton");
        singer.removeAlbum(album);

        singerService.save(singer);
        listSingersWithAlbums(singerService.findAllWithAlbum());
    }

    @Test
    public void testDelete() {
        Singer singer = singerService.findById(2L);
        Assert.assertNotNull(singer);
        singerService.delete(singer);
        listSingersWithAlbums(singerService.findAllWithAlbum());
    }

    @Test
    public void testFindByCriteriaQuery() {
//        List<Singer> singers = singerService.findByCriteriaQuery("John", "Mayer");
        List<Singer> singers = singerService.findByCriteriaQuery("John", null);
//        List<Singer> singers = singerService.findByCriteriaQuery(null, "Mayer");
//        List<Singer> singers = singerService.findByCriteriaQuery(null, null);
        Assert.assertEquals(2, singers.size());
        listSingersWithAlbums(singers);
    }
}
