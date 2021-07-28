package com.ssafy.db.entity.chat;
import com.ssafy.db.entity.board.BoardImage;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


/**
 * 채팅방을 관리할 ChatRoom Entity
 */
@Entity
@Table(name="chat_room", schema = "chat")
@Getter
@Setter
public class ChatRoom extends BaseEntity {


    private long counselingId;   //채팅방과 연관된 공고를 저장할 공고 게시글 ID

    @OneToMany(mappedBy = "roomId", cascade = {CascadeType.ALL}, orphanRemoval=true)
    private List<ChatRoomJoin> chatRoomJoins;

    @OneToMany(mappedBy = "roomId", cascade = {CascadeType.ALL}, orphanRemoval=true)
    private List<ChatMessage> chatMessages;

}
