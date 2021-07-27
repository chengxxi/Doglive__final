package com.ssafy.db.entity.community;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommunityImage is a Querydsl query type for CommunityImage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCommunityImage extends EntityPathBase<CommunityImage> {

    private static final long serialVersionUID = 1343228249L;

    public static final QCommunityImage communityImage = new QCommunityImage("communityImage");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> communityId = createNumber("communityId", Long.class);

    public final StringPath filePath = createString("filePath");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QCommunityImage(String variable) {
        super(CommunityImage.class, forVariable(variable));
    }

    public QCommunityImage(Path<? extends CommunityImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommunityImage(PathMetadata metadata) {
        super(CommunityImage.class, metadata);
    }

}

