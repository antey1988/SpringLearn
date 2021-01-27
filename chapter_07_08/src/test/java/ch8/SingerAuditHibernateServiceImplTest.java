package ch8;

import ch8.config.AuditDataJpaConfig;
import ch8.config.EnversDataJpaConfig;
import ch8.entities.SingerAudit;
import ch8.services.SingerAuditHibernateService;
import ch8.services.SingerAuditService;
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

public class SingerAuditHibernateServiceImplTest {
    private static Logger logger = LoggerFactory.getLogger(SingerAuditHibernateServiceImplTest.class);

    private GenericApplicationContext ctx;
    private SingerAuditHibernateService singerAuditHibernateService;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(EnversDataJpaConfig.class);
        singerAuditHibernateService = ctx.getBean("dataJpaSingerAuditHibernateService", SingerAuditHibernateService.class);
        Assert.assertNotNull(singerAuditHibernateService);
    }

    @After
    public void tearDown() {
        ctx.close();
    }

    private void listSinger(List<SingerAudit> singers) {
        logger.info("--Listing singers without details:--");
        singers.forEach(System.out::println);
        logger.info("------------------------------------");
    }

    private void oneSinger(SingerAudit singer, Long id, int rev) {
        logger.info(String.format("Old Singer with id %d and rev %d:", id, rev));
        System.out.println(singer);
        logger.info("------------------------------------");
    }

    @Test
    public void testAll() {
        List<SingerAudit> singers = singerAuditHibernateService.findAll();
        listSinger(singers);

        logger.info("--Add new singer--");
        SingerAudit singer = new SingerAudit();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(Date.valueOf("1980-04-15"));
        singerAuditHibernateService.save(singer);
        singers = singerAuditHibernateService.findAll();
        listSinger(singers);

        logger.info("--Update singer--");
        singer.setFirstName("John Clayton");
        singerAuditHibernateService.save(singer);
        singers = singerAuditHibernateService.findAll();
        listSinger(singers);

        logger.info("--Update singer--");
        singer = singerAuditHibernateService.findById(1L);
        singer.setFirstName("John");
        singerAuditHibernateService.save(singer);
        singers = singerAuditHibernateService.findAll();
        listSinger(singers);

        SingerAudit oldSinger = singerAuditHibernateService.findByRevision(1L, 1);
        oneSinger(oldSinger, 1L, 1);

        oldSinger = singerAuditHibernateService.findByRevision(1L, 2);
        oneSinger(oldSinger, 1L, 2);

        oldSinger = singerAuditHibernateService.findByRevision(1L, 3);
        oneSinger(oldSinger, 1L, 3);
    }

}
