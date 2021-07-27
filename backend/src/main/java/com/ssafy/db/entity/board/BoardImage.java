package com.ssafy.db.entity.board;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * 게시판 이미지 관련 Entity
 */
@Entity
@Getter
@Setter
public class BoardImage extends BaseEntity {

  private long boardId;             // 공고 아이디
  private long boardType;           // 공고 종류
  private String filePath;          // 이미지 경로


}
