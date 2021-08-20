package com.ssafy.db.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMBTI is a Querydsl query type for MBTI
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMBTI extends EntityPathBase<MBTI> {

    private static final long serialVersionUID = -1636269580L;

    public static final QMBTI mBTI = new QMBTI("mBTI");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath desc = createString("desc");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath name = createString("name");

    public final StringPath title = createString("title");

    public QMBTI(String variable) {
        super(MBTI.class, forVariable(variable));
    }

    public QMBTI(Path<? extends MBTI> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMBTI(PathMetadata metadata) {
        super(MBTI.class, metadata);
    }

}

