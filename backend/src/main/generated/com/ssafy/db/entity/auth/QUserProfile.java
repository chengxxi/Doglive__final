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

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserProfile userProfile = new QUserProfile("userProfile");

    public final ListPath<com.ssafy.db.entity.board.Board, com.ssafy.db.entity.board.QBoard> boards = this.<com.ssafy.db.entity.board.Board, com.ssafy.db.entity.board.QBoard>createList("boards", com.ssafy.db.entity.board.Board.class, com.ssafy.db.entity.board.QBoard.class, PathInits.DIRECT2);

    public final ListPath<Bookmark, QBookmark> bookmarks = this.<Bookmark, QBookmark>createList("bookmarks", Bookmark.class, QBookmark.class, PathInits.DIRECT2);

    public final ListPath<com.ssafy.db.entity.chat.ChatMessage, com.ssafy.db.entity.chat.QChatMessage> chatMessages = this.<com.ssafy.db.entity.chat.ChatMessage, com.ssafy.db.entity.chat.QChatMessage>createList("chatMessages", com.ssafy.db.entity.chat.ChatMessage.class, com.ssafy.db.entity.chat.QChatMessage.class, PathInits.DIRECT2);

    public final ListPath<com.ssafy.db.entity.chat.ChatRoomJoin, com.ssafy.db.entity.chat.QChatRoomJoin> chatRoomJoins = this.<com.ssafy.db.entity.chat.ChatRoomJoin, com.ssafy.db.entity.chat.QChatRoomJoin>createList("chatRoomJoins", com.ssafy.db.entity.chat.ChatRoomJoin.class, com.ssafy.db.entity.chat.QChatRoomJoin.class, PathInits.DIRECT2);

    public final ListPath<com.ssafy.db.entity.board.BoardComment, com.ssafy.db.entity.board.QBoardComment> comments = this.<com.ssafy.db.entity.board.BoardComment, com.ssafy.db.entity.board.QBoardComment>createList("comments", com.ssafy.db.entity.board.BoardComment.class, com.ssafy.db.entity.board.QBoardComment.class, PathInits.DIRECT2);

    public final ListPath<com.ssafy.db.entity.community.Community, com.ssafy.db.entity.community.QCommunity> communities = this.<com.ssafy.db.entity.community.Community, com.ssafy.db.entity.community.QCommunity>createList("communities", com.ssafy.db.entity.community.Community.class, com.ssafy.db.entity.community.QCommunity.class, PathInits.DIRECT2);

    public final ListPath<com.ssafy.db.entity.community.CommunityComment, com.ssafy.db.entity.community.QCommunityComment> communityComments = this.<com.ssafy.db.entity.community.CommunityComment, com.ssafy.db.entity.community.QCommunityComment>createList("communityComments", com.ssafy.db.entity.community.CommunityComment.class, com.ssafy.db.entity.community.QCommunityComment.class, PathInits.DIRECT2);

    public final ListPath<com.ssafy.db.entity.chat.ConferenceHistory, com.ssafy.db.entity.chat.QConferenceHistory> conferenceHistories = this.<com.ssafy.db.entity.chat.ConferenceHistory, com.ssafy.db.entity.chat.QConferenceHistory>createList("conferenceHistories", com.ssafy.db.entity.chat.ConferenceHistory.class, com.ssafy.db.entity.chat.QConferenceHistory.class, PathInits.DIRECT2);

    public final ListPath<com.ssafy.db.entity.chat.Conference, com.ssafy.db.entity.chat.QConference> conferences = this.<com.ssafy.db.entity.chat.Conference, com.ssafy.db.entity.chat.QConference>createList("conferences", com.ssafy.db.entity.chat.Conference.class, com.ssafy.db.entity.chat.QConference.class, PathInits.DIRECT2);

    public final ListPath<com.ssafy.db.entity.chat.ConferenceUser, com.ssafy.db.entity.chat.QConferenceUser> conferenceUsers = this.<com.ssafy.db.entity.chat.ConferenceUser, com.ssafy.db.entity.chat.QConferenceUser>createList("conferenceUsers", com.ssafy.db.entity.chat.ConferenceUser.class, com.ssafy.db.entity.chat.QConferenceUser.class, PathInits.DIRECT2);

    public final ListPath<CounselingHistory, QCounselingHistory> counselingHistories = this.<CounselingHistory, QCounselingHistory>createList("counselingHistories", CounselingHistory.class, QCounselingHistory.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath profileImageUrl = createString("profileImageUrl");

    public final QUser user;

    public QUserProfile(String variable) {
        this(UserProfile.class, forVariable(variable), INITS);
    }

    public QUserProfile(Path<? extends UserProfile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserProfile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserProfile(PathMetadata metadata, PathInits inits) {
        this(UserProfile.class, metadata, inits);
    }

    public QUserProfile(Class<? extends UserProfile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

