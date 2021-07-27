package com.ssafy.db.entity.auth;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserToken is a Querydsl query type for UserToken
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserToken extends EntityPathBase<UserToken> {

    private static final long serialVersionUID = 705255466L;

    public static final QUserToken userToken = new QUserToken("userToken");

    public final StringPath accessToken = createString("accessToken");

    public final StringPath expiresIn = createString("expiresIn");

    public final StringPath id = createString("id");

    public final StringPath refreshToken = createString("refreshToken");

    public final StringPath refreshTokenExpiresIn = createString("refreshTokenExpiresIn");

    public QUserToken(String variable) {
        super(UserToken.class, forVariable(variable));
    }

    public QUserToken(Path<? extends UserToken> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserToken(PathMetadata metadata) {
        super(UserToken.class, metadata);
    }

}

