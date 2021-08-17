package com.ssafy.db.entity.board;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * 강아지 정보 Entity.
 */
@Entity
@Table(name="dog_information", schema = "board")
@Getter
@Setter
public class DogInformation extends BaseEntity {

  @ManyToOne
  @JoinColumn(name="gender")
  private Code gender;                    // 강아지 성별

  @ManyToOne
  @JoinColumn(name = "board_id")
  private Board boardId;

  @Column(name="dog_name")
  private String dogName;                    // 강아지 이름

  @Column(length = 4)
  private String mbti;                    // 강아지 MBTI

  @ManyToOne
  @JoinColumn(name="age_type")
  private Code age;

  private boolean neutralization;            // 강아지 중성화 여부

  @ManyToOne
  @JoinColumn(name="color_type")
  private Code colorType;                 // 강아지 털색 (흰색 / 검정색 / 금색 / 갈색)

  @ManyToOne
  @JoinColumn(name="dog_type")
  private DogType dogType;                  // 강아지 품종

  @ManyToOne
  @JoinColumn(name="weight")
  private Code weight;                    // 강아지 무게 (대(18KG이상) / 중(8-18KG)/ 소(8KG 미만))

  @Column(length = 30)
  private String address;                 // 상세 주소

  @ManyToOne
  @JoinColumn(name="gugun")
  private Gugun gugun;                    //구군 주소

  @Column(columnDefinition = "TEXT")
  private String description;             // 부가 설명


}
