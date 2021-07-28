package com.ssafy.db.entity.community;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommunity is a Querydsl query type for Community
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCommunity extends EntityPathBase<Community> {

    private static final long serialVersionUID = 350328098L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommunity community = new QCommunity("community");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<CommunityComment, QCommunityComment> communityComments = this.<CommunityComment, QCommunityComment>createList("communityComments", CommunityComment.class, QCommunityComment.class, PathInits.DIRECT2);

    public final ListPath<CommunityImage, QCommunityImage> communityImages = this.<CommunityImage, QCommunityImage>createList("communityImages", CommunityImage.class, QCommunityImage.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final TimePath<java.util.Date> regDate = createTime("regDate", java.util.Date.class);

    public final StringPath title = createString("title");

    public final com.ssafy.db.entity.auth.QUserProfile userId;

    public QCommunity(String variable) {
        this(Community.class, forVariable(variable), INITS);
    }

    public QCommunity(Path<? extends Community> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommunity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommunity(PathMetadata metadata, PathInits inits) {
        this(Community.class, metadata, inits);
    }

    public QCommunity(Class<? extends Community> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userId = inits.isInitialized("userId") ? new com.ssafy.db.entity.auth.QUserProfile(forProperty("userId"), inits.get("userId")) : null;
    }

}

