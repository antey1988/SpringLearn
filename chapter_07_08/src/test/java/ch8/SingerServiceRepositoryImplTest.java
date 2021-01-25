package ch8;

import ch8.config.DataJpaConfig;
import ch8.entities.Singer;
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

public class SingerServiceRepositoryImplTest {
    private static Logger logger = LoggerFactory.getLogger(SingerServiceRepositoryImplTest.class);

    private GenericApplicationContext ctx;
    private SingerServiceRepository singerServiceRepository;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(DataJpaConfig.class);
        singerServiceRepository = ctx.getBean(SingerServiceRepository.class);
        Assert.assertNotNull(singerServiceRepository);
    }

    @After
    public void tearDown() {
        ctx.close();
    }

    private void listSingers(List<Singer> singers) {
        logger.info("----Listing singers:");
        singers.forEach(s -> logger.info(s.toString()));
    }

    @Test
    public void testFindAll() {
        List<Singer> singers = singerServiceRepository.findAll();
        Assert.assertTrue(singers.size() > 0);
        listSingers(singers);
    }

    @Test
    public void testFindByFirstName() {
        List<Singer> singers = singerServiceRepository.findByFirstName("John");
        Assert.assertTrue(singers.size() > 0);
        Assert.assertEquals(2, singers.size());
        listSingers(singers);
    }

    @Test
    public void testFirstNameAndLastName() {
        List<Singer> singers = singerServiceRepository.findByFirstNameAndLastName("John", "Mayer");
        Assert.assertTrue(singers.size() > 0);
        Assert.assertEquals(1, singers.size());
        listSingers(singers);
    }
}
