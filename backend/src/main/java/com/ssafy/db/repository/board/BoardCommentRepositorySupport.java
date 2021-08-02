package com.ssafy.db.repository.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.auth.UserProfile;
import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.BoardComment;
import com.ssafy.db.entity.board.QBoardComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BoardCommentRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QBoardComment qBoardComment = QBoardComment.boardComment;

    public Optional<BoardComment> findBoardCommentByBoard(Board board) {
        BoardComment boardComment = jpaQueryFactory.select(qBoardComment).from(qBoardComment)
                .where(qBoardComment.boardId.eq(board)).fetchOne();
        if(boardComment == null) return Optional.empty();
        return Optional.ofNullable(boardComment);
    }
}
