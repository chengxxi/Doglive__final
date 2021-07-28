package com.ssafy.db.entity.auth;

import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.BoardComment;
import com.ssafy.db.entity.chat.*;
import com.ssafy.db.entity.community.Community;
import com.ssafy.db.entity.community.CommunityComment;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 사용자 정보 모델을 정의하는 Entity
 */
@Entity
@Data
@Table(name="user_profile", schema = "auth")
@Getter
@Setter
public class UserProfile {

  @Id
  private String userId;


  @Column(length = 30)
  private String email;

  private String profileImageUrl;

  @Column(length = 10)
  private String name;

  @Column(length = 13)
  private String phoneNumber;


  @OneToMany(mappedBy = "applicantId", cascade = {CascadeType.ALL}, orphanRemoval=true)
  private List<CounselingHistory> counselingHistories;

  @OneToMany(mappedBy = "userId", cascade = {CascadeType.ALL}, orphanRemoval=true)
  private List<Bookmark> bookmarks;


}
