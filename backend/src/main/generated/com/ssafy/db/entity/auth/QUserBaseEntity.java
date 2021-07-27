package com.ssafy.db.entity.auth;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserBaseEntity is a Querydsl query type for UserBaseEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserBaseEntity extends EntityPathBase<UserBaseEntity> {

    private static final long serialVersionUID = -601338237L;

    public static final QUserBaseEntity userBaseEntity = new QUserBaseEntity("userBaseEntity");

    public final StringPath id = createString("id");

    public QUserBaseEntity(String variable) {
        super(UserBaseEntity.class, forVariable(variable));
    }

    public QUserBaseEntity(Path<? extends UserBaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserBaseEntity(PathMetadata metadata) {
        super(UserBaseEntity.class, metadata);
    }

}

