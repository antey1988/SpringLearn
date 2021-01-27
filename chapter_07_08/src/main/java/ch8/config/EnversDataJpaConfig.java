package ch8.config;

import org.hibernate.envers.configuration.EnversSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
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
@ComponentScan(basePackages = {"ch8.services", "ch8.component"})
@EnableJpaRepositories(basePackages = "ch8.repos")
@EnableJpaAuditing(auditorAwareRef = "auditorAwareBean")
public class EnversDataJpaConfig {

    private static Logger logger = LoggerFactory.getLogger(EnversDataJpaConfig.class);

    @Bean
    public DataSource dataSource() {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class);
        return ctx.getBean("embeddedDateBase", DataSource.class);
//        return ctx.getBean("staticDateBase", DataSource.class);
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
        HibernateProperties.put("hibernate.show_sql",false);
        HibernateProperties.put("hibernate.format_sql",true);
        HibernateProperties.put("hibernate.use_sql_comments",true);
        HibernateProperties.put(EnversSettings.AUDIT_TABLE_SUFFIX, "_H");
        HibernateProperties.put(EnversSettings.REVISION_FIELD_NAME, "AUDIT_REVISION");
        HibernateProperties.put(EnversSettings.REVISION_TYPE_FIELD_NAME, "ACTION_TYPE");
        HibernateProperties.put(EnversSettings.AUDIT_STRATEGY, "org.hibernate.envers.strategy.ValidityAuditStrategy");
        HibernateProperties.put(EnversSettings.AUDIT_STRATEGY_VALIDITY_END_REV_FIELD_NAME, "AUDIT_REVISION_END");
        HibernateProperties.put(EnversSettings.AUDIT_STRATEGY_VALIDITY_REVEND_TIMESTAMP_FIELD_NAME, "AUDIT_REVISION_END_TS");
        HibernateProperties.put(EnversSettings.AUDIT_STRATEGY_VALIDITY_STORE_REVEND_TIMESTAMP, "True");
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
