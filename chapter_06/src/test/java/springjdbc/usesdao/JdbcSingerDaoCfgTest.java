package springjdbc.usesdao;

import entryjdbc.entities.Singer;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import springjdbc.usesdao.config.JdbcSingerDaoСfg;
import springjdbc.usesdao.dao.SingerDao;

import java.util.List;

import static org.junit.Assert.*;

public class JdbcSingerDaoCfgTest {

    @Test
    public void testJdbcSingerDao() {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(JdbcSingerDaoСfg.class);
        SingerDao singerDao = ctx.getBean("jdbcSingerDao", SingerDao.class);
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
