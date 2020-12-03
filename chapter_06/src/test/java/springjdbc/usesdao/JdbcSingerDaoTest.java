package springjdbc.usesdao;

import org.junit.*;
import org.springframework.context.support.GenericXmlApplicationContext;
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
    public void testH2DB() {
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
        assertEquals("John Mayer", singerName);
    }

    private void testDaoNotPresent(SingerDao singerDao) {
        assertNotNull(singerDao);
        String singerName = singerDao.findNameById(1L);
        assertNotEquals("Eric Clapton", singerName);
    }
}
