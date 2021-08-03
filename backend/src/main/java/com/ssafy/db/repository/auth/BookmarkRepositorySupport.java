package com.ssafy.db.repository.auth;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.auth.Bookmark;
import com.ssafy.db.entity.auth.QBookmark;
import com.ssafy.db.entity.auth.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookmarkRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QBookmark qBookmark = QBookmark.bookmark;

    public Optional<Bookmark> findBookmarkByUserId(UserProfile userId){
        Bookmark bookmark = jpaQueryFactory.select(qBookmark).from(qBookmark)
                .where(qBookmark.userId.eq(userId)).fetchOne();

        if(bookmark == null)    return Optional.empty();
        return Optional.ofNullable(bookmark);
    }


}
