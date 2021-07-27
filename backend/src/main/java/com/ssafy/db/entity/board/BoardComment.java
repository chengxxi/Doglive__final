package com.ssafy.db.entity.board;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 공고 별 댓글을 저장할 Board Comment Entity
 */
@Entity
@Getter
@Setter
public class BoardComment extends BaseEntity {


  private String userId;                      // 사용자 아이디
  private long boardId;                       // 게시글 번호
  private String comment;                     // 댓글 내용

  @Temporal(TemporalType.TIME)
  private java.util.Date registerDate;        // 댓글 작성 시간

}
