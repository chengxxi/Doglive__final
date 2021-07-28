package com.ssafy.db.entity.auth;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCounselingHistory is a Querydsl query type for CounselingHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCounselingHistory extends EntityPathBase<CounselingHistory> {

    private static final long serialVersionUID = 796609923L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCounselingHistory counselingHistory = new QCounselingHistory("counselingHistory");

    public final QUserProfile applicantId;

    public final com.ssafy.db.entity.board.QBoard boardId;

    public final com.ssafy.db.entity.board.QBoardCategory boardType;

    public final ListPath<com.ssafy.db.entity.chat.ChatRoom, com.ssafy.db.entity.chat.QChatRoom> chatRooms = this.<com.ssafy.db.entity.chat.ChatRoom, com.ssafy.db.entity.chat.QChatRoom>createList("chatRooms", com.ssafy.db.entity.chat.ChatRoom.class, com.ssafy.db.entity.chat.QChatRoom.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath result = createString("result");

    public QCounselingHistory(String variable) {
        this(CounselingHistory.class, forVariable(variable), INITS);
    }

    public QCounselingHistory(Path<? extends CounselingHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCounselingHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCounselingHistory(PathMetadata metadata, PathInits inits) {
        this(CounselingHistory.class, metadata, inits);
    }

    public QCounselingHistory(Class<? extends CounselingHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.applicantId = inits.isInitialized("applicantId") ? new QUserProfile(forProperty("applicantId"), inits.get("applicantId")) : null;
        this.boardId = inits.isInitialized("boardId") ? new com.ssafy.db.entity.board.QBoard(forProperty("boardId"), inits.get("boardId")) : null;
        this.boardType = inits.isInitialized("boardType") ? new com.ssafy.db.entity.board.QBoardCategory(forProperty("boardType")) : null;
    }

}

