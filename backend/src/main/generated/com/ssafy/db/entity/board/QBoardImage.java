package com.ssafy.db.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardImage is a Querydsl query type for BoardImage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoardImage extends EntityPathBase<BoardImage> {

    private static final long serialVersionUID = 1389297119L;

    public static final QBoardImage boardImage = new QBoardImage("boardImage");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final NumberPath<Long> boardType = createNumber("boardType", Long.class);

    public final StringPath filePath = createString("filePath");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QBoardImage(String variable) {
        super(BoardImage.class, forVariable(variable));
    }

    public QBoardImage(Path<? extends BoardImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardImage(PathMetadata metadata) {
        super(BoardImage.class, metadata);
    }

}

