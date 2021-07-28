package com.ssafy.db.repository.board;


import com.ssafy.db.entity.board.BoardCategory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;

@Repository
public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Long> {

}
