package com.emp.EmployeeManagementEx9.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.context.annotation.Primary;
import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.emp.EmployeeManagementEx9.repository",
        entityManagerFactoryRef = "mysqlEntityManagerFactory",
        transactionManagerRef = "mysqlTransactionManager"
)
public class MySQLJpaConfig {

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(DataSource mysqlDataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(mysqlDataSource);
        em.setPackagesToScan("com.emp.EmployeeManagementEx9.entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }

    @Bean
    @Primary
    public JpaTransactionManager mysqlTransactionManager(EntityManagerFactory mysqlEntityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mysqlEntityManagerFactory);
        return transactionManager;
    }
}
