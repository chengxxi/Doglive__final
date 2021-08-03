package com.ssafy.db.entity.chat;
import com.ssafy.db.entity.auth.CounselingHistory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


/**
 * 채팅방을 관리할 ChatRoom Entity
 */
@Entity
@Table(name="chat_room", schema = "chat")
@Getter
@Setter
public class ChatRoom extends BaseEntity {

    @Column(name="counseling_id")
    private Long counselingId;   //상담신청 아이디 연결

    @OneToOne(mappedBy = "chatRoom", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Conference conference;

    @OneToMany(mappedBy = "roomId", cascade = {CascadeType.ALL}, orphanRemoval=true)
    private List<ChatRoomJoin> chatRoomJoins;

    @OneToMany(mappedBy = "roomId", cascade = {CascadeType.ALL}, orphanRemoval=true)
    private List<ChatMessage> chatMessages;

}
