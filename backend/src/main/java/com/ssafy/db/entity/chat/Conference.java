package com.ssafy.db.entity.chat;

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

    private String ownerId;                 //컨퍼런스의 Hose User Id

    private long chatRoomId;                // 채팅방 room Id

    @Temporal(TemporalType.TIME)
    private java.util.Date callStartTime;   //컨퍼런스가 시작한 시간

    @OneToMany(mappedBy = "conferenceId", cascade = {CascadeType.ALL}, orphanRemoval=true)
    private List<ConferenceHistory> conferenceHistories;

    @OneToMany(mappedBy = "conferenceId", cascade = {CascadeType.ALL}, orphanRemoval=true)
    private List<ConferenceUser> conferenceUsers;
}

