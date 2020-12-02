package springjdbc.config;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DbConfigTest {

    private static Logger logger = LoggerFactory.getLogger(DbConfigTest.class);

    @Test
    public void testXmlSpringDriverManager() throws SQLException {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:config/drivermanager-cfg-02.xml");
        ctx.refresh();

        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
        testDataSource(dataSource);
        ctx.close();
    }

    @Test
    public void testApacheDriverManager() throws SQLException {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:config/datasource-dbcp.xml");
        ctx.refresh();

        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
        testDataSource(dataSource);
        ctx.close();
    }

    @Test
    public void testConfigSpringDriverManager() throws SQLException {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class);

        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
        testDataSource(dataSource);
        ctx.close();
    }

    @Test
    public void testXmlDatabaseH2() throws SQLException {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:config/embedded-h2-cfg.xml");
        ctx.refresh();

        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
        testDataSource(dataSource);
        ctx.close();
    }

    @Test
    public void testConfigSpringEmbeddedH2() throws SQLException {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(EmbeddedJdbcConfig.class);

        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
        testDataSource(dataSource);
        ctx.close();
    }

    private void testDataSource(DataSource dataSource) throws SQLException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select 1");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int mockVal = resultSet.getInt(1);
                assertEquals(1, mockVal);
            }
            statement.close();
        } catch (Exception e) {
            logger.debug("Something unexpected happened.", e);
        } finally {
            if (connection != null)
                connection.close();
        }
    }


}
