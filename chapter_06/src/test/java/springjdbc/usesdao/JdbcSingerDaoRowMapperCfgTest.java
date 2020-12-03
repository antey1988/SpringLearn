package springjdbc.usesdao;

import entryjdbc.entities.Singer;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import springjdbc.usesdao.config.JdbcSingerDaoСfg;
import springjdbc.usesdao.dao.SingerDao;

import java.util.List;

import static org.junit.Assert.*;

public class JdbcSingerDaoRowMapperCfgTest {

    @Test
    public void testJdbcSingerDaoRowMapper() {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(JdbcSingerDaoСfg.class);
        SingerDao singerDao = ctx.getBean("jdbcSingerDaoRowMapper", SingerDao.class);
        testDaoOnPresent(singerDao);
        testDaoNotPresent(singerDao);
        testGetThreeRecords(singerDao);
        ctx.close();
    }

    @Test
    public void testJdbcSingerDaoResultSetExtractor() {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(JdbcSingerDaoСfg.class);
        SingerDao singerDao = ctx.getBean("jdbcSingerDaoResultSetExtractor", SingerDao.class);
        testDaoOnPresent(singerDao);
        testDaoNotPresent(singerDao);
        testGetThreeRecords(singerDao);
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

    private void testGetThreeRecords(SingerDao singerDao) {
        assertNotNull(singerDao);
        List<Singer> singers = singerDao.findAll();
        assertEquals(singers.size(), 3);
        testPrintAllRecords(singers);
    }

    private void testPrintAllRecords(List<Singer> list) {
        list.forEach(r->{
            System.out.println(r);
            if (r.getAlbums() != null)
                r.getAlbums().forEach(a->{
                    System.out.println("\t-->" + a);
                });
        });
    }
}
