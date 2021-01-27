package ch8.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
@PropertySource(value = "classpath:config/jdbc.properties", ignoreResourceNotFound = true)
public class DbConfig {

    private static Logger logger = LoggerFactory.getLogger(DbConfig.class);

/*    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${url}")
    private String url;
    @Value("${dbUserName}")
    private String username;
    @Value("${dbUserPass}")
    private String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Lazy
    @Bean("staticDateBase")
    public DataSource staticDateBase() {
        try {
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
            Class<? extends Driver> driver = (Class<? extends Driver>)Class.forName(driverClassName);

            dataSource.setDriverClass(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
//            dataSource.
            return  dataSource;
        } catch (Exception ex) {
            return  null;
        }
    }*/

    @Bean("embeddedDateBase")
    public DataSource embeddedDateBase() {
        try {
            EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
            dbBuilder.setType(EmbeddedDatabaseType.H2);
            dbBuilder.addScripts("classpath:sqlscript/schema_audit_h.sql");
            return dbBuilder.build();
        } catch (Exception e) {
            logger.error("Embedded DataSource bean cannot be created!", e);
            return null;
        }
    }
}
