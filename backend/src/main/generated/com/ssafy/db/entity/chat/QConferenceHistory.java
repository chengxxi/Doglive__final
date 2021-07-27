package com.ssafy.db.entity.chat;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QConferenceHistory is a Querydsl query type for ConferenceHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConferenceHistory extends EntityPathBase<ConferenceHistory> {

    private static final long serialVersionUID = 1696858308L;

    public static final QConferenceHistory conferenceHistory = new QConferenceHistory("conferenceHistory");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> action = createNumber("action", Long.class);

    public final NumberPath<Long> conferenceId = createNumber("conferenceId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DateTimePath<java.sql.Timestamp> regDate = createDateTime("regDate", java.sql.Timestamp.class);

    public final StringPath userId = createString("userId");

    public QConferenceHistory(String variable) {
        super(ConferenceHistory.class, forVariable(variable));
    }

    public QConferenceHistory(Path<? extends ConferenceHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConferenceHistory(PathMetadata metadata) {
        super(ConferenceHistory.class, metadata);
    }

}

