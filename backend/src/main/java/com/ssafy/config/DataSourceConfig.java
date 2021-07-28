package com.ssafy.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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

    @Bean(name="boardDataSource")
    @Qualifier("boardDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.board")
    public DataSource boardDataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name="chatDataSource")
    @Qualifier("chatDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.chat")
    public DataSource chatDataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name="communityDataSource")
    @Qualifier("communityDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.community")
    public DataSource communityDataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}
