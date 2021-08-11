package com.ssafy.db.repository.board;


import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.BoardCategory;
import com.ssafy.db.entity.board.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<List<Board>> findBoardsByUserId(String userId);
    @Transactional
    Optional<List<Board>> deleteBoardsByUserId(String userId);

    @Override
    Optional<Board> findById(Long boardId);

    @Query(
            value = "SELECT * FROM board b WHERE b.board_type = 1 or b.board_type = 2",
            nativeQuery = true
    )
    Optional<List<Board>> findAdoptBoard();


    @Query(
            value = "SELECT * FROM board b WHERE b.board_type = 3 or b.board_type = 4",
            nativeQuery = true
    )
    Optional<List<Board>> findFindBoard();
    
}
