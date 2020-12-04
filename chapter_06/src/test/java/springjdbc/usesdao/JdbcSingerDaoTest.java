package springjdbc.usesdao;

import org.junit.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import springjdbc.usesdao.config.JdbcSingerDaoСfg;
import springjdbc.usesdao.dao.SingerDao;

import static org.junit.Assert.*;

public class JdbcSingerDaoTest {
    @Test
    public void testEmbeddedH2DB() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:config/embedded-h2-cfg.xml");
        ctx.refresh();
        SingerDao singerDao = ctx.getBean("singerDao", SingerDao.class);
        testDaoOnPresent(singerDao);
        testDaoNotPresent(singerDao);
        ctx.close();
    }

    @Test
    public void testH2DBXml() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:config/drivermanager-cfg-02.xml");
        ctx. refresh();
        SingerDao singerDao = ctx.getBean("singerDao", SingerDao.class);
        testDaoOnPresent(singerDao);
        testDaoNotPresent(singerDao);
        ctx.close();
    }

    private void testDaoOnPresent(SingerDao singerDao) {
        assertNotNull(singerDao);
        String singerName = singerDao.findNameById(1L);
        assertEquals("John Clayton Mayer", singerName);
    }

    private void testDaoNotPresent(SingerDao singerDao) {
        assertNotNull(singerDao);
        String singerName = singerDao.findNameById(1L);
        assertNotEquals("Eric Clapton", singerName);
    }
}
