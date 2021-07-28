package com.ssafy.config.querydsl;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.inject.Qualifier;
import javax.sql.DataSource;

/**
 * 공통 DataSource 선언
 */

@Configuration
@EnableConfigurationProperties
public class DataSourceConfig {

    @Bean(name="authDataSource")
    @Qualifier("authDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari.auth")
    public DataSource authDataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}
