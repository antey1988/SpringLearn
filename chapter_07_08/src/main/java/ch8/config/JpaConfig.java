package ch8.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "ch8.services")
@EnableJpaRepositories(basePackages = "ch8.repos")
public class JpaConfig {

    private static Logger logger = LoggerFactory.getLogger(JpaConfig.class);

    @Bean
    public DataSource dataSource() {
        try {
            EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
            dbBuilder.setType(EmbeddedDatabaseType.H2);
            dbBuilder.addScripts("classpath:sqlscript/schema.sql", "classpath:sqlscript/data.sql");
            return dbBuilder.build();
        } catch (Exception e) {
            logger.error("Embedded DataSource bean cannot be created!", e);
            return  null;
        }
    }

    @Bean public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public Properties hibernateProperties() {
        Properties HibernateProperties = new Properties();
        HibernateProperties.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
        HibernateProperties.put("hibernate.max_fetch_depth", 3);
        HibernateProperties.put("hibernate.jdbc.fetch_size",50);
        HibernateProperties.put("hibernate.jdbc.batch_size",10);
        HibernateProperties.put("hibernate.show_sql",true);
        HibernateProperties.put("hibernate.format_sql",true);
        HibernateProperties.put("hibernate.use_sql_comments",true);
        return HibernateProperties;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("ch8.entities");
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.afterPropertiesSet();
        return factoryBean.getNativeEntityManagerFactory();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }
}
