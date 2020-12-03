package springjdbc.usesdao;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import springjdbc.usesdao.config.JdbcSingerСfgH2;
import springjdbc.usesdao.dao.SingerDao;

import static org.junit.Assert.*;

public class JdbcSingerCfgH2Test {
    /*@Test
    public void testEmbeddedH2DB() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:config/embedded-h2-cfg.xml");
        ctx.refresh();
        SingerDao singerDao = ctx.getBean("singerDao", SingerDao.class);
        testDaoOnPresent(singerDao);
        testDaoNotPresent(singerDao);
        ctx.close();
    }*/

    @Test
    public void testH2DB() {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(JdbcSingerСfgH2.class);
        SingerDao singerDao = ctx.getBean("jdbcSingerDao", SingerDao.class);
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
