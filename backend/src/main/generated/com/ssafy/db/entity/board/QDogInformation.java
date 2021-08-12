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

    public final QCode age;

    public final QBoard boardId;

    public final QCode colorType;

    public final StringPath description = createString("description");

    public final StringPath dogName = createString("dogName");

    public final QDogType dogType;

    public final QCode gender;

    public final QGugun gugun;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath mbti = createString("mbti");

    public final BooleanPath neutralization = createBoolean("neutralization");

    public final QCode weight;

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
        this.age = inits.isInitialized("age") ? new QCode(forProperty("age")) : null;
        this.boardId = inits.isInitialized("boardId") ? new QBoard(forProperty("boardId"), inits.get("boardId")) : null;
        this.colorType = inits.isInitialized("colorType") ? new QCode(forProperty("colorType")) : null;
        this.dogType = inits.isInitialized("dogType") ? new QDogType(forProperty("dogType")) : null;
        this.gender = inits.isInitialized("gender") ? new QCode(forProperty("gender")) : null;
        this.gugun = inits.isInitialized("gugun") ? new QGugun(forProperty("gugun"), inits.get("gugun")) : null;
        this.weight = inits.isInitialized("weight") ? new QCode(forProperty("weight")) : null;
    }

}

