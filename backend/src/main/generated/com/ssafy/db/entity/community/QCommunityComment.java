package com.ssafy.db.entity.community;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommunityComment is a Querydsl query type for CommunityComment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCommunityComment extends EntityPathBase<CommunityComment> {

    private static final long serialVersionUID = 1390626525L;

    public static final QCommunityComment communityComment = new QCommunityComment("communityComment");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath comment = createString("comment");

    public final NumberPath<Long> communityId = createNumber("communityId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DateTimePath<java.sql.Timestamp> registerDate = createDateTime("registerDate", java.sql.Timestamp.class);

    public final StringPath userId = createString("userId");

    public QCommunityComment(String variable) {
        super(CommunityComment.class, forVariable(variable));
    }

    public QCommunityComment(Path<? extends CommunityComment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommunityComment(PathMetadata metadata) {
        super(CommunityComment.class, metadata);
    }

}

