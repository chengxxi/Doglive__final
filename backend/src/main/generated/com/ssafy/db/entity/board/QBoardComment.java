package com.ssafy.db.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardComment is a Querydsl query type for BoardComment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoardComment extends EntityPathBase<BoardComment> {

    private static final long serialVersionUID = -1581829661L;

    public static final QBoardComment boardComment = new QBoardComment("boardComment");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final StringPath comment = createString("comment");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final TimePath<java.util.Date> registerDate = createTime("registerDate", java.util.Date.class);

    public final StringPath userId = createString("userId");

    public QBoardComment(String variable) {
        super(BoardComment.class, forVariable(variable));
    }

    public QBoardComment(Path<? extends BoardComment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardComment(PathMetadata metadata) {
        super(BoardComment.class, metadata);
    }

}

