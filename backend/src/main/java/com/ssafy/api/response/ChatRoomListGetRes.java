package com.ssafy.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.auth.Bookmark;
import com.ssafy.db.entity.chat.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomListGetRes extends BaseResponseBody {

    private List<ChatRoomGetRes> chatRoomList;

    public static ChatRoomListGetRes of(Integer statusCode, String message, List<ChatRoomGetRes> chatRoomList){
        ChatRoomListGetRes res = new ChatRoomListGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setChatRoomList(chatRoomList);
        return res;
    }
}
