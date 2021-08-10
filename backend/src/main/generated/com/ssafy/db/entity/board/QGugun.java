package com.ssafy.db.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGugun is a Querydsl query type for Gugun
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGugun extends EntityPathBase<Gugun> {

    private static final long serialVersionUID = 811248520L;

    public static final QGugun gugun = new QGugun("gugun");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final NumberPath<Long> sidoCode = createNumber("sidoCode", Long.class);

    public QGugun(String variable) {
        super(Gugun.class, forVariable(variable));
    }

    public QGugun(Path<? extends Gugun> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGugun(PathMetadata metadata) {
        super(Gugun.class, metadata);
    }

}

