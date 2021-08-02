package com.ssafy.db.repository.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.board.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BoardImageRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QBoardImage qBoardImage = QBoardImage.boardImage;

    public Optional<BoardImage> findBoardImageByBoardId(Board boardId) {
        BoardImage boardImage = jpaQueryFactory.select(qBoardImage).from(qBoardImage)
                .where(qBoardImage.boardId.eq(boardId)).fetchOne();
        if(boardImage == null) return Optional.empty();
        return Optional.ofNullable(boardImage);
    }

}
