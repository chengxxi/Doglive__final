package com.ssafy.db.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardComment is a Querydsl query type for BoardComment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoardComment extends EntityPathBase<BoardComment> {

    private static final long serialVersionUID = -1581829661L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardComment boardComment = new QBoardComment("boardComment");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QBoard boardId;

    public final StringPath comment = createString("comment");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final TimePath<java.util.Date> registerDate = createTime("registerDate", java.util.Date.class);

    public final StringPath userId = createString("userId");

    public QBoardComment(String variable) {
        this(BoardComment.class, forVariable(variable), INITS);
    }

    public QBoardComment(Path<? extends BoardComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardComment(PathMetadata metadata, PathInits inits) {
        this(BoardComment.class, metadata, inits);
    }

    public QBoardComment(Class<? extends BoardComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.boardId = inits.isInitialized("boardId") ? new QBoard(forProperty("boardId"), inits.get("boardId")) : null;
    }

}

