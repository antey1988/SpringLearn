package ch7.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"ch7.config", "ch7.dao"})
@PropertySource("classpath:config/jdbc.properties")
@EnableTransactionManagement
public class AdvincedConfig {
    private static Logger logger = LoggerFactory.getLogger(AdvincedConfig.class);

    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${url}")
    private String url;
    @Value("${dbUserName}")
    private String userName;
    @Value("${dbUserPass}")
    private String userPass;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUrl(url);
            dataSource.setUsername(userName);
            dataSource.setPassword(userPass);
            return dataSource;
        } catch (Exception e) {
            logger.error("DBCP DataSource bean cannot be created!", e);
            return  null;
        }
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws IOException {
        return new HibernateTransactionManager(sessionFactory());
    }

    @Bean
    public SessionFactory sessionFactory() throws IOException {
        return new LocalSessionFactoryBuilder(dataSource())
                .scanPackages("ch7/entities")
                .addProperties(hibernateProperties())
                .buildSessionFactory();
    }

    private Properties hibernateProperties() {
        Properties HibernateProperties = new Properties();
        HibernateProperties.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
        HibernateProperties.put("hibernate.hbm2ddl.auto","create-drop");
        HibernateProperties.put("hibernate.max_fetch_depth", 3);
        HibernateProperties.put("hibernate.jdbc.fetch_size",50);
        HibernateProperties.put("hibernate.jdbc.batch_size",10);
        HibernateProperties.put("hibernate.show_sql",true);
        HibernateProperties.put("hibernate.format_sql",true);
        HibernateProperties.put("hibernate.use_sql_comments",true);
        return HibernateProperties;
    }
}
