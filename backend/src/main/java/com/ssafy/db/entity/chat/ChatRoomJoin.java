package com.ssafy.db.entity.chat;
import com.ssafy.db.entity.auth.User;
import com.ssafy.db.entity.auth.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * 채팅방을 관리할 ChatRoom Entity
 */
@Entity
@Table(name="chat_room_join", schema = "chat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomJoin extends BaseEntity{

    @Column(name= "user_id")
    private String userId;              //참여한 User Id

    @ManyToOne
    @JoinColumn(name="room_id")
    private ChatRoom roomId;                //참여한 채팅 Room Id
}
