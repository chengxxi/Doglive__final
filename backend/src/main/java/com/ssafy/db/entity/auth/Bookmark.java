package com.ssafy.db.entity.auth;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Bookmark Entity
 */
@Entity
@Getter
@Setter
public class Bookmark {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;                                   //bookmark id
  private long boardId;                              //공고 id

  @ManyToOne
  @JoinColumn(name="user_id")
  private UserProfile userId;                             //북마크 등록 user id

}
