package com.ssafy.db.repository.board;


import com.ssafy.db.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Override
    Optional<Board> findById(Long boardId);
}
