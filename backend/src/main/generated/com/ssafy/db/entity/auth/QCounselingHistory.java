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

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final StringPath boardTitle = createString("boardTitle");

    public final StringPath boardType = createString("boardType");

    public final StringPath content = createString("content");

    public final StringPath dogName = createString("dogName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath result = createString("result");

    public final StringPath writer = createString("writer");

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
    }

}

