package com.ssafy.db.repository.auth;

import com.querydsl.jpa.impl.JPAQueryFactory;
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

//    public Optional<UserProfile> findUserById(Long id){
//        UserProfile userProfile = jpaQueryFactory.select(qUser).from(qUser)
//                .where(qUser.id.eq(id)).fetchOne();
//        if(userProfile == null) return Optional.empty();
//        return Optional.ofNullable(userProfile);
//    }
    public Optional<UserProfile> findUserByUserId(User userId) {
        UserProfile user = jpaQueryFactory.select(qUser).from(qUser)
                .where(qUser.userId.eq(userId)).fetchOne();
        if(user == null) return Optional.empty();
        return Optional.ofNullable(user);
    }


}
