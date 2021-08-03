package com.ssafy.db.repository.board;


import com.ssafy.db.entity.auth.User;
import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.BoardComment;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;


@Repository
public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {

//    Optional<BoardComment> findBoardCommentByBoard(Board boardId);


    Optional<List<BoardComment>> findBoardCommentsByBoardId(Board boardId);
}
