package com.devops.developers.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatasourceConfig {
    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.postgres")
    HikariDataSource dataSource(){
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }
}
