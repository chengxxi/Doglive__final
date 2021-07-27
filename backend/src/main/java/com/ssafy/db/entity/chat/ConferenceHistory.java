package com.ssafy.db.entity.chat;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * 회의 이력 Entity
 */
@Entity
@Getter
@Setter
public class ConferenceHistory extends BaseEntity {

    private String userId;                    // 사용자 id
    private long conferenceId;                // 회의 id
    private long action;                      // 회의 상태 (대기 / 진행 / 종료)
    private java.sql.Timestamp regDate;       // 회의 시작일


}

