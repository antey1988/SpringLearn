package ch8;

import ch8.config.DataJpaConfig;
import ch8.entities.Album;
import ch8.entities.Singer;
import ch8.services.AlbumServiceRepository;
import ch8.services.SingerServiceRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class AlbumServiceRepositoryImplTest {
    private static Logger logger = LoggerFactory.getLogger(AlbumServiceRepositoryImplTest.class);

    private GenericApplicationContext ctx;
    private AlbumServiceRepository albumServiceRepository;
    private SingerServiceRepository singerServiceRepository;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(DataJpaConfig.class);
        albumServiceRepository = ctx.getBean(AlbumServiceRepository.class);
        Assert.assertNotNull(albumServiceRepository);
        singerServiceRepository = ctx.getBean(SingerServiceRepository.class);
        Assert.assertNotNull(singerServiceRepository);
    }

    @After
    public void tearDown() {
        ctx.close();
    }

    private void listAlbumss(List<Album> albums) {
        logger.info("----Listing albums:");
        albums.forEach(a -> {
            logger.info(a.toString() +
                    ", Singer: " +
                    a.getSinger().getFirstName() + " " +
                    a.getSinger().getLastName());
        });
    }

    @Test
    public void testFindByTitle() {
        List<Album> albums = albumServiceRepository.findByTitle("The");
        Assert.assertTrue(albums.size() > 0);
        Assert.assertEquals(2, albums.size());
        listAlbumss(albums);
    }

    @Test
    public void testFindBySinger() {
        Singer singer = singerServiceRepository.findByFirstNameAndLastName("John", "Mayer").get(0);
        List<Album> albums = albumServiceRepository.findBySinger(singer);
        Assert.assertTrue(albums.size() > 0);
        Assert.assertEquals(2, albums.size());
        listAlbumss(albums);
    }
}
