package com.ssafy.db.entity.chat;

import com.ssafy.db.entity.auth.User;
import com.ssafy.db.entity.auth.UserProfile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 회의 사용자 Entity : 한 회의에 참여한 사용자 목록 확인 가능
 */
@Entity
@Table(name="conference_user", schema = "chat")
@Getter
@Setter
public class ConferenceUser extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="conference_id")
    private Conference conferenceId;      // 회의 id


    @Column(name="user_id")
    private String userId;          // 사용자 id

}

