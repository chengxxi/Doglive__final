package com.ssafy.db.entity.chat;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConference is a Querydsl query type for Conference
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConference extends EntityPathBase<Conference> {

    private static final long serialVersionUID = -969034320L;

    public static final QConference conference = new QConference("conference");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final TimePath<java.util.Date> callStartTime = createTime("callStartTime", java.util.Date.class);

    public final NumberPath<Long> chatRoomId = createNumber("chatRoomId", Long.class);

    public final ListPath<ConferenceHistory, QConferenceHistory> conferenceHistories = this.<ConferenceHistory, QConferenceHistory>createList("conferenceHistories", ConferenceHistory.class, QConferenceHistory.class, PathInits.DIRECT2);

    public final ListPath<ConferenceUser, QConferenceUser> conferenceUsers = this.<ConferenceUser, QConferenceUser>createList("conferenceUsers", ConferenceUser.class, QConferenceUser.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath ownerId = createString("ownerId");

    public QConference(String variable) {
        super(Conference.class, forVariable(variable));
    }

    public QConference(Path<? extends Conference> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConference(PathMetadata metadata) {
        super(Conference.class, metadata);
    }

}

