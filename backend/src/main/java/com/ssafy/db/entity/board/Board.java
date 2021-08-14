package com.ssafy.db.entity.board;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 유기견 공고 게시물 정보를 담을 Board Entity.
 */


@Entity
@Table(name="board", schema = "board")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Board extends BaseEntity{

  @Column(name="user_id")
  private String userId;                //사용자 ID

  @ManyToOne
  @JoinColumn(name="board_type")
  private BoardCategory type;                    // 공고 타입

  @Column(length = 30)
  private String title;                 // 게시글 제목

  @Column(name="thumbnail_url")
  private String thumbnailUrl;          // 게시글 썸네일


  @Column(name="reg_date")
  private LocalDateTime regDate; // 게시글 등록 일자


  public Board() {
    regDate = LocalDateTime.now();
  }

  @Builder
  public Board(String userId, String title, String thumbnailUrl) {
    this.userId = userId;
    this.title = title;
    this.thumbnailUrl = thumbnailUrl;
    this.regDate =LocalDateTime.now();
  }


  public void addBoardCategory(BoardCategory boardCategory){
    this.type = boardCategory;
  }

}
