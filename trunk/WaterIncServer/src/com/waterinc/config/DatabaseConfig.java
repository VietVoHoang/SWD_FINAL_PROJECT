package com.waterinc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * Created by Asus on 4/14/2017.
 */

@Configuration
@EnableTransactionManagement
// nhiệm vụ đọc cấu hình datasource lên. Từ datasource cung cấp conenction tới DB
public class DatabaseConfig {

    // annotation hỗ trợ DataSource
    @Autowired
    private DataSource dataSource;

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactory.setDataSource(dataSource);

        entityManagerFactory.setPackagesToScan("com.waterinc");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

        Properties additionalProperties = new Properties();

        additionalProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        additionalProperties.put("hibernate.show_sql", "true");
        additionalProperties.put("hibernate.format_sql", "update");
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

        return entityManagerFactory;
    }
}
