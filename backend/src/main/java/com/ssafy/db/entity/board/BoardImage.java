package com.ssafy.db.entity.board;


import lombok.Getter;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.MaybeAliased;

import javax.persistence.*;

/**
 * 게시판 이미지 관련 Entity
 */
@Entity
@Table(name="board_image", schema = "board")
@Getter
@Setter
public class BoardImage extends BaseEntity {

  @ManyToOne
  @JoinColumn(name="board_id")
  private Board boardId;             // 공고 아이디
  private String filePath;          // 이미지 경로


}
