package ch8;

import ch8.config.AuditDataJpaConfig;
import ch8.config.DataJpaConfig;
import ch8.entities.Album;
import ch8.entities.Singer;
import ch8.entities.SingerAudit;
import ch8.services.AlbumServiceRepository;
import ch8.services.SingerAuditService;
import ch8.services.SingerServiceRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.sql.Date;
import java.util.List;

public class SingerAuditServiceImplTest {
    private static Logger logger = LoggerFactory.getLogger(SingerAuditServiceImplTest.class);

    private GenericApplicationContext ctx;
    private SingerAuditService singerAuditService;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(AuditDataJpaConfig.class);
        singerAuditService = ctx.getBean(SingerAuditService.class);
        Assert.assertNotNull(singerAuditService);
    }

    @After
    public void tearDown() {
        ctx.close();
    }

    private void listSinger(List<SingerAudit> singers) {
        logger.info("");
        logger.info("----Listing singers without details:");
        singers.forEach(System.out::println);
    }

    @Test
    public void testAll() {
        List<SingerAudit> singers = singerAuditService.findAll();
        listSinger(singers);

        logger.info("Add new singer");
        SingerAudit singer = new SingerAudit();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(Date.valueOf("1980-04-15"));
        singerAuditService.save(singer);
        singers = singerAuditService.findAll();
        listSinger(singers);


    }

}
