package com.ssafy.db.entity.auth;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 상담 신청 내역 Entity
 */
@Entity
@Getter
@Setter
public class CounselingHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;                  //신청 인덱스 아이디

  @ManyToOne
  @JoinColumn(name="applicant_id")
  private UserProfile applicantId;       //신청자 아이디

  private long boardId;             //입양 공고 아이디
  private long boardType;           //입양 공고 타입

  @Column(columnDefinition = "JSON")
  private String content;           //입양 신청 설문 내용

  @Column(length = 10)
  private String result;            //입양 신청 결과

}
