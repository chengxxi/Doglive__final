package com.ssafy.db.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDogInformation is a Querydsl query type for DogInformation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDogInformation extends EntityPathBase<DogInformation> {

    private static final long serialVersionUID = 41556250L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDogInformation dogInformation = new QDogInformation("dogInformation");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath address = createString("address");

    public final QBoard boardId;

    public final NumberPath<Long> colorType = createNumber("colorType", Long.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> gender = createNumber("gender", Long.class);

    public final NumberPath<Long> hairType = createNumber("hairType", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath mbti = createString("mbti");

    public final NumberPath<Long> neutralization = createNumber("neutralization", Long.class);

    public final NumberPath<Long> weight = createNumber("weight", Long.class);

    public QDogInformation(String variable) {
        this(DogInformation.class, forVariable(variable), INITS);
    }

    public QDogInformation(Path<? extends DogInformation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDogInformation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDogInformation(PathMetadata metadata, PathInits inits) {
        this(DogInformation.class, metadata, inits);
    }

    public QDogInformation(Class<? extends DogInformation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.boardId = inits.isInitialized("boardId") ? new QBoard(forProperty("boardId"), inits.get("boardId")) : null;
    }

}

