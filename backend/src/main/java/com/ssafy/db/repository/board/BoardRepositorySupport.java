package com.ssafy.db.repository.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.board.*;
import org.checkerframework.checker.nullness.Opt;
import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QBoard qBoard = QBoard.board;
    QDogInformation qDogInformation = QDogInformation.dogInformation;

    public Optional<List<Board>> findByBoardsByOption() {
        List<Board> boardList = jpaQueryFactory
                .select(qBoard)
                .from(qBoard)
//                .join(qDogInformation).on(qBoard.id.eq(qDogInformation.boardId.id))
                .fetch();

        if(boardList == null) return Optional.empty();
        return Optional.ofNullable(boardList);
    }
}
