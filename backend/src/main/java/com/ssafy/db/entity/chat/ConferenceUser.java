package com.ssafy.db.entity.chat;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 회의 사용자 Entity : 한 회의에 참여한 사용자 목록 확인 가능
 */
@Entity
@Table(name="conference_user", schema = "chat")
@Getter
@Setter
public class ConferenceUser extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="Conference_ID")
    private Conference conferenceId;      // 회의 id

    private String userId;          // 사용자 id


}

