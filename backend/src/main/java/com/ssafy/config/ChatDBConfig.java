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

@Configuration                                                                      //application.yaml에서 어떤 properties를 읽을지 지정
@EnableTransactionManagement
@EnableJpaRepositories(                                                             //Jpa에 관한 설정 및 파일의 위치 명시
        entityManagerFactoryRef = "chatEntityManagerFactory",
        transactionManagerRef = "chatTransactionManager",
        basePackages = "com.ssafy.db.repository.chat"                               //repository의 위치
)
public class ChatDBConfig extends HikariConfig {

    @Autowired
    @Qualifier("chatDataSource")
    private DataSource chatDataSource;

    @Bean(name="chatEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean chatEntityManagerFactory(EntityManagerFactoryBuilder builder){
        return builder.dataSource(chatDataSource)
                .packages("com.ssafy.db.entity.chat")
                .persistenceUnit("chatEntityManager")
                .build();
    }

    @Bean("chatTransactionManager")
    public PlatformTransactionManager chatTransactionManager(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(chatEntityManagerFactory(builder).getObject());
    }

}