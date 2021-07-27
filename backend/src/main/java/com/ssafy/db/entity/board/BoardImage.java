package com.ssafy.db.entity.board;


import lombok.Getter;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.MaybeAliased;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * 게시판 이미지 관련 Entity
 */
@Entity
@Getter
@Setter
public class BoardImage extends BaseEntity {

  @ManyToOne
  @JoinColumn(name="board_id")
  private Board boardId;             // 공고 아이디
  private String filePath;          // 이미지 경로


}
