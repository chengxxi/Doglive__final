package com.ssafy.db.entity.chat;

import com.ssafy.db.entity.auth.UserProfile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 화상회의 정보를 관리할 Conference Entity
 */
@Entity
@Table(name="conference", schema = "chat")
@Getter
@Setter
public class Conference extends BaseEntity{

    @Column(name="owner_id")
    private String ownerId;                     //컨퍼런스의 Hose User Id

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="chat_room_id")
    private ChatRoom chatRoomId;                // 채팅방 room Id

    @Column(name="call_start_time")
    @Temporal(TemporalType.TIME)
    private java.util.Date callStartTime;   //컨퍼런스가 시작한 시간

    @OneToMany(mappedBy = "conferenceId", cascade = {CascadeType.ALL}, orphanRemoval=true)
    private List<ConferenceHistory> conferenceHistories;

    @OneToMany(mappedBy = "conferenceId", cascade = {CascadeType.ALL}, orphanRemoval=true)
    private List<ConferenceUser> conferenceUsers;
}

