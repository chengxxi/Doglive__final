package com.ssafy.db.entity.chat;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * 회의 사용자 Entity : 한 회의에 참여한 사용자 목록 확인 가능
 */
@Entity
@Getter
@Setter
public class ConferenceUser extends BaseEntity {

    private long conferenceId;      // 회의 id
    private String userId;          // 사용자 id


}

