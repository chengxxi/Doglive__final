package com.ssafy.db.repository.auth;

import com.ssafy.db.entity.auth.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {


}
