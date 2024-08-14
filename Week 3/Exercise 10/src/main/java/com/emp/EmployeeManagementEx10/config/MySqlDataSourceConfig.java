package com.emp.EmployeeManagementEx10.config;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MySqlDataSourceConfig {
    
    @ConfigurationProperties("spring.datasource.mysql")
    @Bean
    @Primary
    DataSourceProperties mysqlDataSourceProperties(){
        return new DataSourceProperties();
    }
    
    @Bean
    @Primary
    DataSource mysqlDataSource(){
        return mysqlDataSourceProperties().initializeDataSourceBuilder().build();
    }
}