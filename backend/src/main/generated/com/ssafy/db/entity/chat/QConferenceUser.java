package com.ssafy.db.entity.chat;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QConferenceUser is a Querydsl query type for ConferenceUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConferenceUser extends EntityPathBase<ConferenceUser> {

    private static final long serialVersionUID = 1614003611L;

    public static final QConferenceUser conferenceUser = new QConferenceUser("conferenceUser");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> conferenceId = createNumber("conferenceId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath userId = createString("userId");

    public QConferenceUser(String variable) {
        super(ConferenceUser.class, forVariable(variable));
    }

    public QConferenceUser(Path<? extends ConferenceUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConferenceUser(PathMetadata metadata) {
        super(ConferenceUser.class, metadata);
    }

}

