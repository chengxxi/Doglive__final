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

    public static final QChatRoom chatRoom = new QChatRoom("chatRoom");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<ChatMessage, QChatMessage> chatMessages = this.<ChatMessage, QChatMessage>createList("chatMessages", ChatMessage.class, QChatMessage.class, PathInits.DIRECT2);

    public final ListPath<ChatRoomJoin, QChatRoomJoin> chatRoomJoins = this.<ChatRoomJoin, QChatRoomJoin>createList("chatRoomJoins", ChatRoomJoin.class, QChatRoomJoin.class, PathInits.DIRECT2);

    public final NumberPath<Long> counselingId = createNumber("counselingId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QChatRoom(String variable) {
        super(ChatRoom.class, forVariable(variable));
    }

    public QChatRoom(Path<? extends ChatRoom> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatRoom(PathMetadata metadata) {
        super(ChatRoom.class, metadata);
    }

}

