package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.BoardComment;
import com.ssafy.db.entity.board.BoardImage;
import com.ssafy.db.entity.board.DogInformation;
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
public class ChatMessageGetRes {

    private Long id; // message id

    private String userId;

    private String username;

    private String chatMessage;

    private LocalDateTime sendTimeAt;

    // DB 저장용인 ChatMessage에서 사용자에게 보여줄 만큼의 정보만 가져옴
    public static ChatMessageGetRes copy(ChatMessage message){
        ChatMessageGetRes res = new ChatMessageGetRes();
        res.setId(message.getId());
        res.setUserId(message.getUserId());
        res.setChatMessage(message.getMessage());
        res.setSendTimeAt(message.getSendTimeAt());
        return res;
    }

}
