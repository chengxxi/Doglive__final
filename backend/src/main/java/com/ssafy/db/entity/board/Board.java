package com.ssafy.db.entity.board;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 유기견 공고 게시물 정보를 담을 Board Entity.
 */



@Entity
@SqlResultSetMapping(
        name="BoardandDogMapping",
        classes = @ConstructorResult(
                targetClass = BoardListData.class,
                columns = {
                        @ColumnResult(name="id", type = Long.class),
                        @ColumnResult(name="user_id", type = String.class),
                        @ColumnResult(name="board_type", type = Long.class),
                        @ColumnResult(name="title", type = String.class),
                        @ColumnResult(name="thumbnail_url", type = String.class),
                        @ColumnResult(name="reg_date", type = LocalDateTime.class),
                        @ColumnResult(name="gender", type = Long.class),
                        @ColumnResult(name="dog_name", type = String.class),
                        @ColumnResult(name="mbti", type = String.class),
                        @ColumnResult(name="age_type", type = Long.class),
                        @ColumnResult(name="color_type", type = Long.class),
                        @ColumnResult(name="dog_type", type = Long.class),
                        @ColumnResult(name="weight", type = Long.class),
                })
)
@NamedNativeQuery(
        name="BoardAndDogwithPaging",
        query="SELECT b.id, b.user_id, b.board_type, b.title, b.thumbnail_url, b.reg_date, d.gender, d.dog_name, d.mbti, d.age_type, d.color_type, d.dog_type, d.weight "
                + "FROM board b LEFT JOIN dog_information d "
                + "ON b.id = d.board_id "
                + "WHERE b.board_type = :type1 OR b.board_type = :type2 "
                + "ORDER BY b.reg_date DESC "
        +"LIMIT :limit OFFSET :offset",
        resultSetMapping="BoardandDogMapping")
@Table(name="board", schema = "board")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Board extends BaseEntity{

  @Column(name="user_id")
  private String userId;                //사용자 ID

  @ManyToOne
  @JoinColumn(name="board_type")
  private BoardCategory type;                    // 공고 타입

  @Column(length = 30)
  private String title;                 // 게시글 제목

  @Column(name="thumbnail_url")
  private String thumbnailUrl;          // 게시글 썸네일


  @Column(name="reg_date")
  private LocalDateTime regDate; // 게시글 등록 일자


  public Board() {
    regDate = LocalDateTime.now();
  }

//  @OneToMany(mappedBy = "boardId", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval=true)
//  private List<BoardComment> boardComments;
//
//  @OneToMany(mappedBy = "boardId", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval=true)
//  private Set<BoardImage> boardImages;
//
//  @OneToMany(mappedBy = "boardId", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval=true)
//  private Set<DogInformation> dogInformations;                   // 유기견 ID

}
