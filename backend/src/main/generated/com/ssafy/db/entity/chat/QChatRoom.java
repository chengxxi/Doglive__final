package com.ssafy.db.entity.chat;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatRoom is a Querydsl query type for ChatRoom
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QChatRoom extends EntityPathBase<ChatRoom> {

    private static final long serialVersionUID = 349544935L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatRoom chatRoom = new QChatRoom("chatRoom");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<ChatMessage, QChatMessage> chatMessages = this.<ChatMessage, QChatMessage>createList("chatMessages", ChatMessage.class, QChatMessage.class, PathInits.DIRECT2);

    public final ListPath<ChatRoomJoin, QChatRoomJoin> chatRoomJoins = this.<ChatRoomJoin, QChatRoomJoin>createList("chatRoomJoins", ChatRoomJoin.class, QChatRoomJoin.class, PathInits.DIRECT2);

    public final QConference conference;

    public final com.ssafy.db.entity.auth.QCounselingHistory counselingId;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QChatRoom(String variable) {
        this(ChatRoom.class, forVariable(variable), INITS);
    }

    public QChatRoom(Path<? extends ChatRoom> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatRoom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatRoom(PathMetadata metadata, PathInits inits) {
        this(ChatRoom.class, metadata, inits);
    }

    public QChatRoom(Class<? extends ChatRoom> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.conference = inits.isInitialized("conference") ? new QConference(forProperty("conference"), inits.get("conference")) : null;
        this.counselingId = inits.isInitialized("counselingId") ? new com.ssafy.db.entity.auth.QCounselingHistory(forProperty("counselingId"), inits.get("counselingId")) : null;
    }

}

