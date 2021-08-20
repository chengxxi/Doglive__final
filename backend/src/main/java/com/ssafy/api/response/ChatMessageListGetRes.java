package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.chat.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageListGetRes extends BaseResponseBody {

    private List<ChatMessageGetRes> messageList;

    public static ChatMessageListGetRes of(Integer statusCode, String message, List<ChatMessageGetRes> messageList) {
        ChatMessageListGetRes res = new ChatMessageListGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setMessageList(messageList);
        return res;
    }
}
