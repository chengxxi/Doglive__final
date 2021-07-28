package com.ssafy.db.entity.chat;
import com.ssafy.db.entity.auth.CounselingHistory;
import com.ssafy.db.entity.board.BoardImage;
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


    @ManyToOne
    @JoinColumn(name = "counseling_id")
    private CounselingHistory counselingId;   //상담신청 아이디 연결

    @OneToOne(mappedBy = "chatRoomId")
    private Conference conference;

    @OneToMany(mappedBy = "roomId", cascade = {CascadeType.ALL}, orphanRemoval=true)
    private List<ChatRoomJoin> chatRoomJoins;

    @OneToMany(mappedBy = "roomId", cascade = {CascadeType.ALL}, orphanRemoval=true)
    private List<ChatMessage> chatMessages;

}
