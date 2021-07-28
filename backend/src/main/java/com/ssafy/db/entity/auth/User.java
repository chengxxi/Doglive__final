package com.ssafy.db.entity.auth;

import com.ssafy.db.entity.board.BoardImage;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 사용자 모델을 정의하는 Entity
 */
@Entity
@Data
@Table(name="user", schema = "auth")
@Getter
@Setter
public class User {
  @Id
  @Column(length = 13)
  private String id;    // 사용자의 Kakao Id

  @OneToOne @MapsId
  UserProfile userProfile;


}
