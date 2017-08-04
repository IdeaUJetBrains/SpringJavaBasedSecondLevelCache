package com.test.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * SpringConfiguration
 * Created on 22/06/2017 09:30
 *
 * @author Florian RUYNAT <florian.ruynat@ext.ombudsman.europa.eu>
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.test.repositories", queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND)
@ComponentScan({
        "com.test.entities"})
@PropertySource(value = "classpath:application.properties",ignoreResourceNotFound = true, encoding = "UTF-8")
public class MyAppConf {

    // Database
    private String driverClassName = "org.hsqldb.jdbc.JDBCDriver";
    private String databaseUrl = "jdbc:hsqldb:file:C:/Users/Olga Pavlova/IdeaProjects/jpa/db/DB";
    private String databaseUsername = "sa";
    private String databasePassword = "";

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPersistenceUnitName("JPA");
        em.setPackagesToScan("com.test.entities");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Boolean> props = new HashMap<>();
        props.put("hibernate.cache.use_second_level_cache", true);
        //props.put("hibernate.cache.region.factory_class", true);
        em.setJpaPropertyMap(props);
        // Try properties this way ? :/
        em.setJpaProperties(additionalProperties());
        return em;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.cache.use_second_level_cache", String.valueOf(true));
        properties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        properties.setProperty("hibernate.cache.provider_class", "net.sf.ehcache.hibernate.SingletonEhCacheRegionFactory");
        properties.setProperty("hibernate.cache.use_query_cache", String.valueOf(true));
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
