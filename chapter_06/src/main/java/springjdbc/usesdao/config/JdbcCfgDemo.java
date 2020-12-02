package springjdbc.usesdao.config;

import org.junit.*;
import org.springframework.context.support.GenericXmlApplicationContext;
import springjdbc.usesdao.dao.SingerDao;

import static org.junit.Assert.*;

public class JdbcCfgDemo {
    @Test
    public void testH2DB() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:config/embedded-h2-cfg.xml");
        ctx. refresh();
    }

    private void testDao(SingerDao singerDao) {
        assertNotNull(singerDao);
        String singerName = singerDao.findNameById(1L);
        assertEquals("John Mayer", singerName);
    }
}
