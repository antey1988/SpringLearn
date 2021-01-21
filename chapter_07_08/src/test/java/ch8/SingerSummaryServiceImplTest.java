package ch8;

import ch8.config.JpaConfig;
import ch8.entities.Singer;
import ch8.services.SingerSummaryService;
import ch8.services.SingerSummaryServiceImpl;
import ch8.services.SingerSummaryUntypeImpl;
import ch8.view.SingerSummary;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class SingerSummaryServiceImplTest {
    private static Logger logger = LoggerFactory.getLogger(SingerSummaryServiceImplTest.class);

    private GenericApplicationContext ctx;
    private SingerSummaryService singerSummaryService;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        singerSummaryService = ctx.getBean(SingerSummaryService.class);
        Assert.assertNotNull(singerSummaryService);
    }

    @After
    public void tearDown() {
        ctx.close();
    }

    @Test
    public void testFindAllSingerSummary() {
        List<SingerSummary> singerSummaries = singerSummaryService.findAll();
        Assert.assertEquals(2, singerSummaries.size());
        listSingersSummary(singerSummaries);
    }

    private void listSingersSummary(List<SingerSummary> singers) {
        logger.info("----Listing singers summary:");
        singers.forEach(s -> logger.info(s.toString()));
    }
}
