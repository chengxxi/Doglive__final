package com.ssafy.db.entity.auth;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 사용자 정보 모델을 정의하는 Entity
 */
@Entity
@Getter
@Setter
public class UserProfile {

  @Id
  @Column(length = 13)
  private String id;    // 사용자의 Kakao Id

  @Column(length = 30)
  private String email;

  private String profileImageUrl;

  @Column(length = 10)
  private String name;

  @Column(length = 13)
  private String phoneNumber;

  @OneToMany(mappedBy = "userId", cascade = {CascadeType.ALL}, orphanRemoval=true)
  private List<UserToken> userTokens;

  @OneToMany(mappedBy = "userId", cascade = {CascadeType.ALL}, orphanRemoval=true)
  private List<CounselingHistory> counselingHistories;

  @OneToMany(mappedBy = "userId", cascade = {CascadeType.ALL}, orphanRemoval=true)
  private List<Bookmark> bookmarks;

}
