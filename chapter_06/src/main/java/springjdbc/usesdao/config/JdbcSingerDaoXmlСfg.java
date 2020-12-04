package springjdbc.usesdao.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
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
//@ImportResource("classpath:config/embedded-h2-cfg.xml")

public class JdbcSingerDaoXmlСfg {
    private static Logger logger = LoggerFactory.getLogger(JdbcSingerDaoXmlСfg.class);
    /*@Autowired
    private DataSource dataSource;

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }*/

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
        dbBuilder.setType(EmbeddedDatabaseType.H2);
        return dbBuilder.addScripts("classpath:sqlscript/schema.sql", "classpath:sqlscript/data.sql").build();

    }

    @Bean
    public JdbcSingerDaoResultSetExtractor jdbcSingerDaoResultSetExtractor() {
        JdbcSingerDaoResultSetExtractor jdbcSingerDaoResultSetExtractor = new JdbcSingerDaoResultSetExtractor();
        jdbcSingerDaoResultSetExtractor.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate());
        return jdbcSingerDaoResultSetExtractor;
    }
}
