package com.ssafy.db.entity.chat;

import com.ssafy.db.entity.auth.UserProfile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 회의 이력 Entity
 */
@Entity
@Table(name="conference_history", schema = "chat")
@Getter
@Setter
public class ConferenceHistory extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserProfile userId;                    // 사용자 id

    @ManyToOne
    @JoinColumn(name="conference_id")
    private Conference conferenceId;                // 회의 id

    private long action;                      // 회의 상태 (대기 / 진행 / 종료)

    @Column(name="reg_date")
    private java.sql.Timestamp regDate;       // 회의 시작일


}

