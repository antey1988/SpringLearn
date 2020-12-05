package springjdbc.usesdao.config;

import entryjdbc.entities.Singer;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.Date;

@Configuration
@PropertySource(value = "classpath:config/jdbc2.properties")
@ComponentScan(basePackages = "springjdbc.usesdao.annot")
public class AnnotJdbcSingerDaoСfg {
    private static Logger logger = LoggerFactory.getLogger(AnnotJdbcSingerDaoСfg.class);

    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${url}")
    private String url;
    @Value("${dbusername}")
    private String username;
    @Value("${dbpassword}")
    private String password;
    @Value("${firstname}")
    private String first_name;
    @Value("${lastname}")
    private String last_name;
    @Value("${birthdate}")
    private String birth_date;
    @Value("${idd}")
    private Long id;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() {
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            return  dataSource;
        } catch (Exception ex) {
            logger.error("DBCP DataSource bean cannot be created!", ex);
            return  null;
        }
    }

    @Bean
    public Singer singer() {
        Singer singer = new Singer();
        singer.setId(id);
        singer.setFirstName(first_name);
        singer.setLastName(last_name);
        singer.setBirthDate(Date.valueOf(birth_date));
        return singer;
    }

}
