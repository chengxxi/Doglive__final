package com.ssafy.config;

import com.google.common.collect.ImmutableMap;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
                       //application.yaml에서 어떤 properties를 읽을지 지정
@EnableTransactionManagement
@EnableJpaRepositories(                                                             //Jpa에 관한 설정 및 파일의 위치 명시
        entityManagerFactoryRef = "boardEntityManagerFactory",
        transactionManagerRef = "boardTransactionManager",
        basePackages = "com.ssafy.db.repository.board"                               //repository의 위치
)
public class BoardDBConfig extends HikariConfig {


    @Autowired
    @Qualifier("boardDataSource")
    private DataSource boardDataSource;

    @Bean(name="boardEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean boardEntityManagerFactory(EntityManagerFactoryBuilder builder){
        return builder.dataSource(boardDataSource)
                .packages("com.ssafy.db.entity.board")
                .persistenceUnit("boardEntityManager")
                .build();
    }

    @Bean("boardTransactionManager")
    public PlatformTransactionManager boardTransactionManager(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(boardEntityManagerFactory(builder).getObject());
    }

}