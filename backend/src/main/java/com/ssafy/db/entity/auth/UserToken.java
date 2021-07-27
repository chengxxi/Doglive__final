package com.ssafy.db.entity.auth;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Kakao에서 발급된 토큰 관련 Entity
 */
@Entity
@Getter
@Setter
public class UserToken extends  UserBaseEntity{

  private String kakaoToken;      // 카카오 토큰

}
