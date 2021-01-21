package ch8;

import ch8.config.JpaConfig;
import ch8.entities.Singer;
import ch8.services.SingerService;
import ch8.services.SingerSummaryUntypeImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Collections;
import java.util.List;

public class SingerSummaryUntypeImplTest {
    private static Logger logger = LoggerFactory.getLogger(SingerSummaryUntypeImplTest.class);

    private GenericApplicationContext ctx;
    private SingerSummaryUntypeImpl singerSummaryUntypeImpl;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        singerSummaryUntypeImpl = ctx.getBean(SingerSummaryUntypeImpl.class);
        Assert.assertNotNull(singerSummaryUntypeImpl);
    }

    @After
    public void tearDown() {
        ctx.close();
    }

    @Test
    public void testdisplayAllSingerSummary() {
        singerSummaryUntypeImpl.displayAllSingerSummary();
    }


}
