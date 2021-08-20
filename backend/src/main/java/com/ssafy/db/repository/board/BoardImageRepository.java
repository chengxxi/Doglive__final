package com.ssafy.db.repository.board;


import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.BoardImage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.Optional;

@Repository
public interface BoardImageRepository extends JpaRepository<BoardImage, Long> {

    Optional<List<BoardImage>> findBoardImagesByBoardId(Board board);
    @Transactional
    void deleteBoardImagesByBoardId(Board board);

    @Transactional
    Optional<List<BoardImage>> findBoardImagesByImgFullPath(String url);

    BoardImage findFirstByBoardIdOrderById(Board board);
}
