package com.ssafy.db.entity.chat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


/**
 * 채팅방을 관리할 ChatRoom Entity
 */
@Entity
@Table(name="chat_room", schema = "chat")
@Getter
@Setter
@NoArgsConstructor
public class ChatRoom extends BaseEntity {

    @Column(name="counseling_id")
    private Long counselingId;   //상담신청 아이디 연결
}
