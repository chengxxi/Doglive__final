package com.ssafy.db.entity.auth;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Bookmark Entity
 */
@Entity
@Data
@Table(name="bookmark", schema = "auth")
@Getter
@Setter
public class Bookmark {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id = null;                                //bookmark id

  @Column(name="board_id")
  private long boardId;                              //공고 id

  @ManyToOne
  @JoinColumn(name="user_id")
  private UserProfile userId;                             //북마크 등록 user id

}
