package com.ssafy.db.repository.board;


import com.ssafy.db.entity.board.BoardComment;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;


@Repository
public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {


}
