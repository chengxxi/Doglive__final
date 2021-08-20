package com.ssafy.db.repository.auth;

import com.ssafy.db.entity.auth.Bookmark;
import com.ssafy.db.entity.auth.User;
import com.ssafy.db.entity.auth.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.awt.print.Book;
import java.util.List;
import java.util.Optional;


@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Optional<Bookmark> findBookmarkByBoardIdAndUserId(Long boardId, UserProfile userId);
    Optional<Bookmark> findBookmarkByUserId(UserProfile userId);
    Optional<List<Bookmark>> findBookmarksByUserId(UserProfile userId);
    @Transactional
    void deleteBookmarksByUserId(UserProfile userId);

}
