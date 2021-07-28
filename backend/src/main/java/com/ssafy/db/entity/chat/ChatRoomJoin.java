package com.ssafy.db.entity.chat;
import com.ssafy.db.entity.auth.UserProfile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 채팅방을 관리할 ChatRoom Entity
 */
@Entity
@Table(name="chat_room_join", schema = "chat")
@Getter
@Setter
public class ChatRoomJoin extends BaseEntity{

    @ManyToOne
    @JoinColumn(name= "user_id")
    private UserProfile userId;              //참여한 User Id

    @ManyToOne
    @JoinColumn(name="room_id")
    private ChatRoom roomId;                //참여한 채팅 Room Id
}
