package com.ssafy.db.entity.auth;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCounselingHistory is a Querydsl query type for CounselingHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCounselingHistory extends EntityPathBase<CounselingHistory> {

    private static final long serialVersionUID = 796609923L;

    public static final QCounselingHistory counselingHistory = new QCounselingHistory("counselingHistory");

    public final StringPath applicantId = createString("applicantId");

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final NumberPath<Long> boardType = createNumber("boardType", Long.class);

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath result = createString("result");

    public QCounselingHistory(String variable) {
        super(CounselingHistory.class, forVariable(variable));
    }

    public QCounselingHistory(Path<? extends CounselingHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCounselingHistory(PathMetadata metadata) {
        super(CounselingHistory.class, metadata);
    }

}

