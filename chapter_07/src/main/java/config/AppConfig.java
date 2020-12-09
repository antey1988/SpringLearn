package config;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Driver;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"config", "dao"})
@PropertySource("classpath:config/jdbc.properties")
@EnableTransactionManagement
public class AppConfig {
    private static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${url}")
    private String url;
    @Value("${dbUserName}")
    private String userName;
    @Value("${dbUserPass}")
    private String userPass;

    @Bean
    public DataSource dataSource() {
        /*try {
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
            dataSource.setDriverClass((Class<? extends Driver>)Class.forName(driverClassName));
            dataSource.setUrl(url);
            dataSource.setUsername(userName);
            dataSource.setPassword(userPass);
            return dataSource;
        } catch (ClassNotFoundException e) {
            logger.error("DBCP DataSource bean cannot be created!", e);
            return  null;
        }*/

        try {
            EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
            return dbBuilder.setType(EmbeddedDatabaseType.H2)
                    .addScripts("classpath:sqlscript/schema.sql", "classpath:sqlscript/data.sql")
                    .build();
        } catch (Exception e) {
            logger.error("Embedded DataSource bean cannot be create!", e);
            return null;
        }
    }

    @Bean
    public HibernateTransactionManager transactionManager() throws IOException {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory());
        return hibernateTransactionManager;
    }

    @Bean
    public SessionFactory sessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("entities");
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.afterPropertiesSet();
        return sessionFactory.getObject();
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.max_fetch_depth", 3);
        properties.put("hibernate.jdbc.fetch_size",50);
        properties.put("hibernate.jdbc.batch_size",10);
        properties.put("hibernate.show_sql",true);
        properties.put("hibernate.format_sql",true);
        properties.put("hibernate.use_sql_comments",true);
        return properties;
    }
}
