package com.ssafy.db.repository.auth;

import java.util.Optional;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.auth.QUser;
import com.ssafy.db.entity.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class UserRepositorySupport {


    private JPAQueryFactory jpaQueryFactory;
    QUser qUser = QUser.user;

    public Optional<User> findUserById(String id) {
        User user = jpaQueryFactory.select(qUser).from(qUser)
                .where(qUser.id.eq(id)).fetchOne();
        if(user==null) return Optional.empty();
        return Optional.ofNullable(user);
    }
}
