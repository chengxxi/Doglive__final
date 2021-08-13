package com.ssafy.db.entity.chat;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatMessageRead is a Querydsl query type for ChatMessageRead
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QChatMessageRead extends EntityPathBase<ChatMessageRead> {

    private static final long serialVersionUID = 682314673L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatMessageRead chatMessageRead = new QChatMessageRead("chatMessageRead");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final BooleanPath isRead = createBoolean("isRead");

    public final QChatMessage messageId;

    public final QChatRoom roomId;

    public final StringPath userId = createString("userId");

    public QChatMessageRead(String variable) {
        this(ChatMessageRead.class, forVariable(variable), INITS);
    }

    public QChatMessageRead(Path<? extends ChatMessageRead> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatMessageRead(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatMessageRead(PathMetadata metadata, PathInits inits) {
        this(ChatMessageRead.class, metadata, inits);
    }

    public QChatMessageRead(Class<? extends ChatMessageRead> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.messageId = inits.isInitialized("messageId") ? new QChatMessage(forProperty("messageId"), inits.get("messageId")) : null;
        this.roomId = inits.isInitialized("roomId") ? new QChatRoom(forProperty("roomId")) : null;
    }

}

