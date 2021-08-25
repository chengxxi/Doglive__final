package com.ssafy.db.entity.chat;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConferenceUser is a Querydsl query type for ConferenceUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConferenceUser extends EntityPathBase<ConferenceUser> {

    private static final long serialVersionUID = 1614003611L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConferenceUser conferenceUser = new QConferenceUser("conferenceUser");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QConference conferenceId;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath userId = createString("userId");

    public QConferenceUser(String variable) {
        this(ConferenceUser.class, forVariable(variable), INITS);
    }

    public QConferenceUser(Path<? extends ConferenceUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QConferenceUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QConferenceUser(PathMetadata metadata, PathInits inits) {
        this(ConferenceUser.class, metadata, inits);
    }

    public QConferenceUser(Class<? extends ConferenceUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.conferenceId = inits.isInitialized("conferenceId") ? new QConference(forProperty("conferenceId"), inits.get("conferenceId")) : null;
    }

}

