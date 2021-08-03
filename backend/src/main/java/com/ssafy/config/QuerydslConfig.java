package com.ssafy.config;


import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class QuerydslConfig {

    @PersistenceContext(unitName = "authEntityManager")
    EntityManager authEntityManager;

//    @PersistenceContext(unitName = "boardEntityManager")
//    EntityManager boardEntityManager;

    @PersistenceContext(unitName = "chatEntityManager")
    EntityManager chatEntityManager;

    @PersistenceContext(unitName = "communityEntityManager")
    EntityManager communityEntityManager;

    @Bean(name="authQueryFactory")
    public JPAQueryFactory authQueryFactory() {
        return new JPAQueryFactory(authEntityManager);
    }

//    @Bean(name="boardQueryFactory")
//    public JPAQueryFactory boardQueryFactory() {
//        return new JPAQueryFactory(boardEntityManager);
//    }

    @Bean(name="chatQueryFactory")
    public JPAQueryFactory chatQueryFactory() {
        return new JPAQueryFactory(chatEntityManager);
    }

    @Bean(name="communityQueryFactory")
    public JPAQueryFactory communityQueryFactory() {
        return new JPAQueryFactory(communityEntityManager);
    }
}
