package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Driver;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("repos")
public class DataJpaConfig {
    private static Logger logger = LoggerFactory.getLogger(DataJpaConfig.class);

    @SuppressWarnings("unchecked")
    @Bean
    public DataSource dataSource() {
        try {
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
            Class<? extends Driver> driver = (Class<? extends Driver>) Class.forName("org.h2.Driver");
            dataSource.setDriverClass(driver);
            dataSource.setUrl("jdbc:h2:/home/oleg/IdeaProjects/Spring/chapter_09/src/test/resources/database/MUSIC");
            dataSource.setUsername("dba");
            dataSource.setPassword("sql");
            return dataSource;
        } catch (Exception e) {
            logger.error("Populator DataSourse bean cannot be created!", e);
            return null;
        }
    }

    @Bean
    public Properties hibernateProperties() {
        Properties HibernateProperties = new Properties();
        HibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        HibernateProperties.put("hibernate.hbm2ddl.auto", "create-drop");
        HibernateProperties.put("hibernate.hbm2ddl.import_files", "/scripts/data.sql");
        HibernateProperties.put("hibernate.max_fetch_depth", 3);
        HibernateProperties.put("hibernate.jdbc.fetch_size", 50);
        HibernateProperties.put("hibernate.jdbc.batch_size", 10);
        HibernateProperties.put("hibernate.show_sql", true);
        HibernateProperties.put("hibernate.format_sql", true);
        HibernateProperties.put("hibernate.use_sql_comments", true);
        return HibernateProperties;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setPackagesToScan("entities");
        factoryBean.afterPropertiesSet();
        return factoryBean.getNativeEntityManagerFactory();
    }
}
