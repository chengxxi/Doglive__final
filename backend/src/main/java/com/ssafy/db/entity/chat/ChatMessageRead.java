package com.ssafy.db.entity.chat;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 채팅의 Read여부를 관리할 ChatMessageRead Entity
 */
@Entity
@Table(name="chat_message_read", schema = "chat")
@Getter
@Setter
public class ChatMessageRead extends BaseEntity {

    @Column(name="user_id")
    private String userId;          //채팅방에 있는 유저

    @ManyToOne
    @JoinColumn(name="room_id", nullable = false)
    private ChatRoom roomId;            //채팅방 ID

    @ManyToOne
    @JoinColumn(name="message_id", nullable = false)
    private ChatMessage messageId;

    @Column(name="is_read", columnDefinition = "TINYINT(1) default false")
    private boolean isRead;
}
