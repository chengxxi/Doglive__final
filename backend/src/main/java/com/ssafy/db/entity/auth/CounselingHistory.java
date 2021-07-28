package com.ssafy.db.entity.auth;

import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.BoardCategory;
import com.ssafy.db.entity.chat.ChatMessage;
import com.ssafy.db.entity.chat.ChatRoom;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 상담 신청 내역 Entity
 */
@Entity
@Data
@Table(name="counseling_history", schema = "auth")
@Getter
@Setter
public class CounselingHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;                  //신청 인덱스 아이디

  @ManyToOne
  @JoinColumn(name="applicant_id")
  private UserProfile applicantId;       //신청자 아이디

  @Column(name="board_id")
  private Long boardId;             //입양 공고 아이디

  @Column(name="board_type")
  private Long boardType;           //입양 공고 타입

  @Column(columnDefinition = "JSON")
  private String content;           //입양 신청 설문 내용

  @Column(length = 10)
  private String result;            //입양 신청 결과


}
