package com.ssafy.db.entity.auth;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * 사용자 모델을 정의하는 Entity
 */
@Entity
@Getter
@Setter
public class User extends UserBaseEntity{
  private String kakaoId;       // 사용자의 Kakao Id

}
