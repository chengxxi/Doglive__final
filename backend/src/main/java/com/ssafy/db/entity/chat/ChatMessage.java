package com.ssafy.db.entity.chat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 채팅 데이터를 저장할 ChatMessage Entity
 */
@Entity
@Getter
@Setter
public class ChatMessage extends BaseEntity {

    private String userId;          //채팅을 보낸 사용자 ID
    private long roomId;            //채팅방 ID
    private String message;         //채팅 Message 데이터

    @Temporal(TemporalType.TIME)
    private java.util.Date time;    //채팅 보낸 시간


}
