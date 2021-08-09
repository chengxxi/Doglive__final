package com.ssafy.db.entity.chat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 채팅방을 관리할 ChatRoom Entity
 */
@Entity
@Table(name="chat_room", schema = "chat")
@Getter
@Setter
@NoArgsConstructor
public class ChatRoom extends BaseEntity {

//    @Column(name="counseling_id")
//    private Long counselingId;   //상담신청 아이디 연결

    @Column
    private String name;

    public ChatRoom(Long id, String name){
        this.id = id;
        this.name = name;
    }

    // @OneToMany(mappedBy = "chatRoom", fetch = FetchType.EAGER)
    // private List<ChatMessage> messages = new ArrayList<>();

//    public void addMessage(ChatMessage message){
//        messages.add(message);
//    }
}
