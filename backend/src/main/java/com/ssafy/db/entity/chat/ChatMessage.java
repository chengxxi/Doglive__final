package com.ssafy.db.entity.chat;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.compilermsgs.qual.CompilerMessageKey;

import javax.persistence.*;

/**
 * 채팅 데이터를 저장할 ChatMessage Entity
 */
@Entity
@Table(name="chat_message", schema = "chat")
@Getter
@Setter
public class ChatMessage extends BaseEntity {

    private String userId;          //채팅을 보낸 사용자 ID

    @ManyToOne
    @JoinColumn(name="room_id")
    private ChatRoom roomId;            //채팅방 ID

    @Column(columnDefinition = "TEXT")
    private String message;         //채팅 Message 데이터

    @Temporal(TemporalType.TIME)
    private java.util.Date time;    //채팅 보낸 시간


}
