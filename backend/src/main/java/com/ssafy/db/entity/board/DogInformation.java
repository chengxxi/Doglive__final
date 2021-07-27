package com.ssafy.db.entity.board;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 강아지 정보 Entity
 */
@Entity
@Getter
@Setter

public class DogInformation extends BaseEntity {

  private long gender;                    // 강아지 성별

  @Column(length = 4)
  private String mbti;                    // 강아지 MBTI
  private long neutralization;            // 강아지 중성화 여부
  private long colorType;                 // 강아지 털색 (흰색 / 검정색 / 금색 / 갈색)
  private long hairType;                  // 강아지 모 종류 (단모 / 장모)
  private long weight;                    // 강아지 무게 (대(18KG이상) / 중(8-18KG)/ 소(8KG 미만))

  @Column(length = 4)
  private String address;                 // 발견일 + 발견 장소 / 실종일 + 실종 장소

  @Column(columnDefinition = "TEXT")
  private String description;             // 부가 설명


}
