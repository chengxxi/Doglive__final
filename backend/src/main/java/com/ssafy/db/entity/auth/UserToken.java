package com.ssafy.db.entity.auth;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Kakao에서 발급된 토큰 관련 Entity
 */
@Entity
@Data
@Table(name="user_token", schema = "auth")
@Getter
@Setter
public class UserToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id = null;

  @ManyToOne
  @JoinColumn(name="user_id")
  private User userId;    // 사용자의 Kakao Id

  @Column(name="access_token")
  private String accessToken;

  @Column(name="refresh_token")
  private String refreshToken;

}
