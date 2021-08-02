package com.ssafy.db.repository.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.BoardImage;
import com.ssafy.db.entity.board.QBoard;
import com.ssafy.db.entity.board.QBoardImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BoardRepositorySupport {

//    @Autowired
//    private JPAQueryFactory jpaQueryFactory;
//
//    QBoard qBoard = QBoard.board;
//
//    public Optional<Board> findBoardById(Long boardId) {
//        Board board = jpaQueryFactory.select(qBoard).from(qBoard)
//                .where(qBoard.id.eq(boardId)).fetchOne();
//        if(board == null) return Optional.empty();
//        return Optional.ofNullable(board);
//    }

}
