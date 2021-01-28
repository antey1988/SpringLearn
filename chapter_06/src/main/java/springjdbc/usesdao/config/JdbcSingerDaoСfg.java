package springjdbc.usesdao.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import springjdbc.usesdao.JdbcSingerDao;
import springjdbc.usesdao.JdbcSingerDaoResultSetExtractor;
import springjdbc.usesdao.JdbcSingerDaoRowMapper;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
//@PropertySource(value = "classpath:config/jdbc2.properties")
public class JdbcSingerDaoСfg {
    private static Logger logger = LoggerFactory.getLogger(JdbcSingerDaoСfg.class);
/*
    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${url}")
    private String url;
    @Value("${dbusername}")
    private String username;
    @Value("${dbpassword}")
    private String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() {
        try {
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
            Class<? extends Driver> driver = (Class<? extends Driver>)Class.forName(driverClassName);

            dataSource.setDriverClass(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            return  dataSource;
        } catch (Exception ex) {
            return  null;
        }*/
    @Bean
    public DataSource dataSource() {
        try {
        EmbeddedDatabaseBuilder db = new EmbeddedDatabaseBuilder();
        db.setType(EmbeddedDatabaseType.H2);
        db.addScripts("classpath:sqlscript/schema.sql", "classpath:sqlscript/data.sql");
        return db.build();
        } catch (Exception e) {
            logger.error("Embedded DataSource bean cannot be created!", e);
            return null;
        }
    }


    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public JdbcSingerDao jdbcSingerDao() {
        JdbcSingerDao jdbcSingerDao = new JdbcSingerDao();
        jdbcSingerDao.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate());
        return jdbcSingerDao;
    }

    @Bean
    public JdbcSingerDaoRowMapper jdbcSingerDaoRowMapper() {
        JdbcSingerDaoRowMapper jdbcSingerDaoRowMapper = new JdbcSingerDaoRowMapper();
        jdbcSingerDaoRowMapper.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate());
        return jdbcSingerDaoRowMapper;
    }

    @Bean
    public JdbcSingerDaoResultSetExtractor jdbcSingerDaoResultSetExtractor() {
        JdbcSingerDaoResultSetExtractor jdbcSingerDaoResultSetExtractor = new JdbcSingerDaoResultSetExtractor();
        jdbcSingerDaoResultSetExtractor.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate());
        return jdbcSingerDaoResultSetExtractor;
    }
}
