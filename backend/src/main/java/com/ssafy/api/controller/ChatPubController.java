package com.ssafy.api.controller;

import com.ssafy.api.request.ChatMessagePostReq;
import com.ssafy.api.response.ChatMessageGetRes;
import com.ssafy.api.service.ChatService;
import com.ssafy.api.service.UserService;
import com.ssafy.db.entity.chat.ChatMessage;

import com.ssafy.db.entity.chat.ChatMessageRead;
import com.ssafy.db.entity.chat.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ChatPubController {

    // @EnableWebSocketMessageBroker에 의해 등록되는 bean. 특정 Broker로 메시지 전달
    private final SimpMessagingTemplate template;

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @Autowired
    public ChatPubController(SimpMessagingTemplate template){
        this.template = template;
    }

    // MessageMapping은 client가 Send할 수 있는 경로로, config에서 등록한 prefix와 합쳐진다
    // 요청경로 = "/pub/chat/join"
//    @MessageMapping("/chat/join")
//    public void join(ChatMessagePostReq message){
//        message.setChatMessage(message.getUserId() + "님이 입장하셨습니다.");
//        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
//    }

    // 요청경로 = "/pub/chat/message"
    @MessageMapping("/chat/message")
    public void message(ChatMessagePostReq req){
        ChatMessage msg = chatService.saveChatMessage(req); // chat_message 테이블에 메세지 저장
        ChatRoom chatRoom = chatService.getChatRoomInfo(req.getRoomId());

        List<String> idList = chatService.getUserIdList(chatRoom);
        for(int i = 0 ; i < idList.size(); i++) {
            ChatMessageRead read = new ChatMessageRead();
            read.setMessageId(msg);
            read.setUserId(idList.get(i));
            read.setRoomId(chatRoom);

            if (req.getUserId().equals(idList.get(i))) // 자신이 보낸 메세지면 True (읽음 처리)
                read.setRead(true);
            else
                read.setRead(false);
            chatService.saveRead(read);
        }
        ChatMessageGetRes message = ChatMessageGetRes.copy(msg);
        message.setUsername(userService.getUserName(msg.getUserId()));

        template.convertAndSend("/sub/chat/room/" + req.getRoomId(), message);
    }
}
