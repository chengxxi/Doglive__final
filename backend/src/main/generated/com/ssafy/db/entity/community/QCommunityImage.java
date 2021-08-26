package com.ssafy.db.entity.community;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommunityImage is a Querydsl query type for CommunityImage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCommunityImage extends EntityPathBase<CommunityImage> {

    private static final long serialVersionUID = 1343228249L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommunityImage communityImage = new QCommunityImage("communityImage");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QCommunity communityId;

    public final StringPath filename = createString("filename");

    public final StringPath filePath = createString("filePath");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QCommunityImage(String variable) {
        this(CommunityImage.class, forVariable(variable), INITS);
    }

    public QCommunityImage(Path<? extends CommunityImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommunityImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommunityImage(PathMetadata metadata, PathInits inits) {
        this(CommunityImage.class, metadata, inits);
    }

    public QCommunityImage(Class<? extends CommunityImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.communityId = inits.isInitialized("communityId") ? new QCommunity(forProperty("communityId")) : null;
    }

}

