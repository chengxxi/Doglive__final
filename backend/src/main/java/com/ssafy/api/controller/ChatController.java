package com.ssafy.api.controller;

import com.ssafy.db.entity.chat.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Profile("stomp")
@Controller
public class ChatController {

    // @EnableWebSocketMessageBroker에 의해 등록되는 bean. 특정 Broker로 메시지 전달
    private final SimpMessagingTemplate template;

    @Autowired
    public ChatController(SimpMessagingTemplate template){
        this.template = template;
    }

    // MessageMapping은 client가 Send할 수 있는 경로로, config에서 등록한 prefix와 합쳐진다
    // 요청경로 = "/pub/chat/join"
    @MessageMapping("/chat/join")
    public void join(ChatMessage message){
        message.setMessage(message.getUserId() + "님이 입장하셨습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId());
    }

    // 요청경로 = "/pub/chat/message"
    @MessageMapping("/chat/message")
    public void message(ChatMessage message){
        template.convertAndSend("/sub/chat/room/" + message.getRoomId());
    }

//    @MessageMapping("/chat.join")
//    @SendTo("/sub/chat/room")
//    public ChatMessage join(@Payload ChatMessage msg, SimpMessageHeaderAccessor header){
//        header.getSessionAttributes().put("userid", msg.getUserId());
//        return msg;
//    }
//
//    @MessageMapping("/chat.message")
//    @SendTo("/sub/chat/room")
//    public ChatMessage message(@Payload ChatMessage msg){
//        return msg;
//    }
}
