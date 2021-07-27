package com.ssafy.db.entity.chat;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QChatRoomJoin is a Querydsl query type for ChatRoomJoin
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QChatRoomJoin extends EntityPathBase<ChatRoomJoin> {

    private static final long serialVersionUID = -1946703951L;

    public static final QChatRoomJoin chatRoomJoin = new QChatRoomJoin("chatRoomJoin");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> roomId = createNumber("roomId", Long.class);

    public final StringPath userId = createString("userId");

    public QChatRoomJoin(String variable) {
        super(ChatRoomJoin.class, forVariable(variable));
    }

    public QChatRoomJoin(Path<? extends ChatRoomJoin> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatRoomJoin(PathMetadata metadata) {
        super(ChatRoomJoin.class, metadata);
    }

}

