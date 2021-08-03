package com.ssafy.db.entity.board;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 게시판 타입을 저장할 Board Entity.
 */
@Entity
@Table(name="board_category", schema = "board")
@Getter
@Setter
public class BoardCategory extends BaseEntity {

  @Column(length = 2)
  private String name;    // 타입 명 (입양, 임보, 실종, 보호)


}
