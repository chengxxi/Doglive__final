package com.ssafy.db.entity.community;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommunityComment is a Querydsl query type for CommunityComment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCommunityComment extends EntityPathBase<CommunityComment> {

    private static final long serialVersionUID = 1390626525L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommunityComment communityComment = new QCommunityComment("communityComment");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath comment = createString("comment");

    public final QCommunity communityId;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final BooleanPath isDelete = createBoolean("isDelete");

    public final StringPath name = createString("name");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath userId = createString("userId");

    public QCommunityComment(String variable) {
        this(CommunityComment.class, forVariable(variable), INITS);
    }

    public QCommunityComment(Path<? extends CommunityComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommunityComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommunityComment(PathMetadata metadata, PathInits inits) {
        this(CommunityComment.class, metadata, inits);
    }

    public QCommunityComment(Class<? extends CommunityComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.communityId = inits.isInitialized("communityId") ? new QCommunity(forProperty("communityId")) : null;
    }

}

