package com.ssafy.db.entity.auth;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * 사용자 정보 모델을 정의하는 Entity
 */
@Entity
@Getter
@Setter
public class UserProfile extends UserBaseEntity{

  private String email;
  private String profileImageUrl;
  private String name;
  private String phoneNumber;

}
