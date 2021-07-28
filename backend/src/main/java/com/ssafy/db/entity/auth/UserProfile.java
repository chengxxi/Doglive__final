package com.ssafy.db.entity.auth;

import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.BoardComment;
import com.ssafy.db.entity.chat.*;
import com.ssafy.db.entity.community.Community;
import com.ssafy.db.entity.community.CommunityComment;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * 사용자 정보 모델을 정의하는 Entity
 */
@Entity
@Data
@Table(name="user_profile", schema = "auth")
@Getter
@Setter
public class UserProfile {

  @Id
  @OneToOne
  @JoinColumn(name= "id")
  private User user;


  @Column(length = 30)
  private String email;

  private String profileImageUrl;

  @Column(length = 10)
  private String name;

  @Column(length = 13)
  private String phoneNumber;


  @OneToMany(mappedBy = "applicantId", cascade = {CascadeType.ALL}, orphanRemoval=true)
  private List<CounselingHistory> counselingHistories;

  @OneToMany(mappedBy = "userId", cascade = {CascadeType.ALL}, orphanRemoval=true)
  private List<Bookmark> bookmarks;

  @OneToMany(mappedBy = "userId", cascade = {CascadeType.ALL}, orphanRemoval=true)
  private List<Board> boards;

  @OneToMany(mappedBy = "userId", cascade = {CascadeType.ALL}, orphanRemoval=true)
  private List<BoardComment> comments;

  @OneToMany(mappedBy = "userId", cascade = {CascadeType.ALL}, orphanRemoval=true)
  private List<ChatMessage> chatMessages;

  @OneToMany(mappedBy = "userId", cascade = {CascadeType.ALL}, orphanRemoval=true)
  private List<ChatRoomJoin> chatRoomJoins;

  @OneToMany(mappedBy = "ownerId", cascade = {CascadeType.ALL}, orphanRemoval=true)
  private List<Conference> conferences;

  @OneToMany(mappedBy = "userId", cascade = {CascadeType.ALL}, orphanRemoval=true)
  private List<ConferenceHistory> conferenceHistories;

  @OneToMany(mappedBy = "userId", cascade = {CascadeType.ALL}, orphanRemoval=true)
  private List<ConferenceUser> conferenceUsers;

  @OneToMany(mappedBy = "userId", cascade = {CascadeType.ALL}, orphanRemoval=true)
  private List<Community> communities;

  @OneToMany(mappedBy = "userId", cascade = {CascadeType.ALL}, orphanRemoval=true)
  private List<CommunityComment> communityComments;

}
