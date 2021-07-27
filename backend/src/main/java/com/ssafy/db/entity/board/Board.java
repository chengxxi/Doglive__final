package com.ssafy.db.entity.board;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 유기견 공고 게시물 정보를 담을 Board Entity
 */
@Entity
@Getter
@Setter
public class Board extends BaseEntity{

  private String userId;                //사용자 ID
  private long dogId;                   // 유기견 ID
  private long type;                    // 공고 타입
  private String title;                 // 게시글 제목
  private String thumbnailUrl;          // 게시글 썸네일

  @Temporal(TemporalType.TIME)
  private java.util.Date registerDate; // 게시글 등록 일자



}
