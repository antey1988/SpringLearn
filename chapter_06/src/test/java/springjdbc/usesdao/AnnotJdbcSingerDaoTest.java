package springjdbc.usesdao;

import static org.junit.Assert.*;

import entryjdbc.entities.Album;
import entryjdbc.entities.Singer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import springjdbc.usesdao.config.AnnotJdbcSingerDaoСfg;
import springjdbc.usesdao.dao.SingerDao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AnnotJdbcSingerDaoTest {
//    private static Logger logger = LoggerFactory.getLogger(AnnotJdbcSingerDaoTest.class);
    private GenericApplicationContext ctx;
    private SingerDao singerDao;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(AnnotJdbcSingerDaoСfg.class);
        singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
    }

    @After
    public void tearDown() {
        ctx.close();
    }

    @Test
    public void testFindAll() {
        List<Singer> singers = singerDao.findAll();
        assertEquals(3, singers.size());
        printSingers(singers);
    }

    @Test
    public void testFindByFirstName() {
        List<Singer> singers = singerDao.findByFirstName("John Clayton");
        assertEquals(1, singers.size());
        printSingers(singers);
    }

    @Test
    public void testUpdate() {
        Singer singer = ctx.getBean(Singer.class);
        List<Singer> singers = singerDao.findAll();
        assertEquals(3, singers.size());
        printSingers(singers);
        singerDao.update(singer);
        singers = singerDao.findAll();
        assertEquals(3, singers.size());
        printSingers(singers);
    }

    @Test
    public void testInsert() {
        List<Singer> singers = singerDao.findAll();
        assertEquals(3, singers.size());
        printSingers(singers);

        Singer singer = new Singer();
        singer.setFirstName("Ed");
        singer.setLastName("Sheeran");
        singer.setBirthDate(Date.valueOf("1991-1-17"));

        singerDao.insert(singer);
        singers = singerDao.findAll();
        assertEquals(4, singers.size());
        printSingers(singers);
    }

    @Test
    public void testInsertSingerWithAlbums() {
        printSingers(singerDao.findAllWithAlbums());

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
        singerDao.insertWithAlbums(singer);

        printSingers(singerDao.findAllWithAlbums());
    }

    private void printSingers(List<Singer> singers) {
        singers.forEach(singer->{
            System.out.println(singer);
            if (singer.getAlbums() != null) {
                singer.getAlbums().forEach(album-> System.out.println("--> " + album));
            }
        });
    }
}
