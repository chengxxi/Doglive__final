package com.ssafy.db.repository.auth;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.auth.QUser;
import com.ssafy.db.entity.auth.QUserProfile;
import com.ssafy.db.entity.auth.User;
import com.ssafy.db.entity.auth.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserProfileRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QUserProfile qUser = QUserProfile.userProfile;

    public Optional<UserProfile> findUserByUser(User u) {
        UserProfile user = jpaQueryFactory.select(qUser).from(qUser)
                .where(qUser.userId.eq(u)).fetchOne();
        if(user == null) return Optional.empty();
        return Optional.ofNullable(user);
    }
}
