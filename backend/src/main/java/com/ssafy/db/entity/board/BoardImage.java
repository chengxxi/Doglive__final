package com.ssafy.db.entity.board;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.MaybeAliased;

import javax.persistence.*;

/**
 * 게시판 이미지 관련 Entity.
 */
@Entity
@Table(name="board_image", schema = "board")
@Getter
@Setter
@NoArgsConstructor
public class BoardImage extends BaseEntity {

  @ManyToOne
  @JoinColumn(name="board_id")
  private Board boardId;             // 공고 아이디

  private String url;

  @Column(name="origin_file_name")
  private String originFileName;

  @Builder
  public BoardImage(String url, String originFileName){
    this.url = url;
    this.originFileName = originFileName;
  }

  public void addBoard(Board board){
    this.boardId = board;
  }

}
