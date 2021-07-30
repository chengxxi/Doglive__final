package com.ssafy.db.entity.auth;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -217251057L;

    public static final QUser user = new QUser("user");

    public final StringPath id = createString("id");

    public final ListPath<UserProfile, QUserProfile> userProfiles = this.<UserProfile, QUserProfile>createList("userProfiles", UserProfile.class, QUserProfile.class, PathInits.DIRECT2);

    public final ListPath<UserToken, QUserToken> userTokens = this.<UserToken, QUserToken>createList("userTokens", UserToken.class, QUserToken.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

