package com.ssafy.db.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDogInformation is a Querydsl query type for DogInformation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDogInformation extends EntityPathBase<DogInformation> {

    private static final long serialVersionUID = 41556250L;

    public static final QDogInformation dogInformation = new QDogInformation("dogInformation");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath address = createString("address");

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
        super(DogInformation.class, forVariable(variable));
    }

    public QDogInformation(Path<? extends DogInformation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDogInformation(PathMetadata metadata) {
        super(DogInformation.class, metadata);
    }

}

