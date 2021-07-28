package com.ssafy.db.repository.board;


import com.ssafy.db.entity.board.BoardImage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Repository
public interface BoardImageRepository extends JpaRepository<BoardImage, Long> {


}
