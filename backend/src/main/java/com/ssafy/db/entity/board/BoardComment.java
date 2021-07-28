package com.ssafy.db.entity.board;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 공고 별 댓글을 저장할 Board Comment Entity
 */
@Entity
@Table(name="board_comment", schema = "board")
@Getter
@Setter
public class BoardComment extends BaseEntity {

  @Column(length = 13)
  private String userId;                      // 사용자 아이디

  @ManyToOne
  @JoinColumn(name="board_id")
  private Board boardId;                       // 게시글 번호

  @Column(columnDefinition = "TEXT")
  private String comment;                     // 댓글 내용

  @Temporal(TemporalType.TIME)
  private java.util.Date registerDate;        // 댓글 작성 시간

}
