package com.ssafy.db.entity.board;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 유기견 공고 게시물 정보를 담을 Board Entity
 */
@Entity
@Table(name="board", schema = "board")
@Getter
@Setter
public class Board extends BaseEntity{

  @Column(length = 13)
  private String userId;                //사용자 ID

  @OneToOne
  @JoinColumn(name="dog_id")
  private DogInformation dogId;                   // 유기견 ID

  @ManyToOne
  @JoinColumn(name="board_type")
  private BoardCategory type;                    // 공고 타입

  @Column(length = 30)
  private String title;                 // 게시글 제목

  @Column(length = 255)
  private String thumbnailUrl;          // 게시글 썸네일

  @Temporal(TemporalType.TIME)
  private java.util.Date registerDate; // 게시글 등록 일자

  @OneToMany(mappedBy = "boardId", cascade = {CascadeType.ALL}, orphanRemoval=true)
  private List<BoardComment> boardComments;

  @OneToMany(mappedBy = "boardId", cascade = {CascadeType.ALL}, orphanRemoval=true)
  private List<BoardImage> boardImages;



}
