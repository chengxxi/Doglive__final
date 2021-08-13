package com.ssafy.db.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDogType is a Querydsl query type for DogType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDogType extends EntityPathBase<DogType> {

    private static final long serialVersionUID = -614507764L;

    public static final QDogType dogType = new QDogType("dogType");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public QDogType(String variable) {
        super(DogType.class, forVariable(variable));
    }

    public QDogType(Path<? extends DogType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDogType(PathMetadata metadata) {
        super(DogType.class, metadata);
    }

}

