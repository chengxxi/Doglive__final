package com.ssafy.db.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 806446300L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<BoardComment, QBoardComment> boardComments = this.<BoardComment, QBoardComment>createList("boardComments", BoardComment.class, QBoardComment.class, PathInits.DIRECT2);

    public final ListPath<BoardImage, QBoardImage> boardImages = this.<BoardImage, QBoardImage>createList("boardImages", BoardImage.class, QBoardImage.class, PathInits.DIRECT2);

    public final QDogInformation dogId;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final TimePath<java.util.Date> regDate = createTime("regDate", java.util.Date.class);

    public final StringPath thumbnailUrl = createString("thumbnailUrl");

    public final StringPath title = createString("title");

    public final QBoardCategory type;

    public final StringPath userId = createString("userId");

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dogId = inits.isInitialized("dogId") ? new QDogInformation(forProperty("dogId"), inits.get("dogId")) : null;
        this.type = inits.isInitialized("type") ? new QBoardCategory(forProperty("type")) : null;
    }

}

