package com.ssafy.db.entity.auth;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserProfile is a Querydsl query type for UserProfile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserProfile extends EntityPathBase<UserProfile> {

    private static final long serialVersionUID = -19767398L;

    public static final QUserProfile userProfile = new QUserProfile("userProfile");

    public final ListPath<Bookmark, QBookmark> bookmarks = this.<Bookmark, QBookmark>createList("bookmarks", Bookmark.class, QBookmark.class, PathInits.DIRECT2);

    public final ListPath<CounselingHistory, QCounselingHistory> counselingHistories = this.<CounselingHistory, QCounselingHistory>createList("counselingHistories", CounselingHistory.class, QCounselingHistory.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath profileImageUrl = createString("profileImageUrl");

    public final ListPath<UserToken, QUserToken> userTokens = this.<UserToken, QUserToken>createList("userTokens", UserToken.class, QUserToken.class, PathInits.DIRECT2);

    public QUserProfile(String variable) {
        super(UserProfile.class, forVariable(variable));
    }

    public QUserProfile(Path<? extends UserProfile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserProfile(PathMetadata metadata) {
        super(UserProfile.class, metadata);
    }

}

