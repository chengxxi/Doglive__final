package com.ssafy.api.controller;


import com.ssafy.api.response.ChatMessageGetRes;
import com.ssafy.api.response.ChatMessageListGetRes;
import com.ssafy.api.response.ChatRoomListGetRes;
import com.ssafy.api.service.ChatService;
import com.ssafy.db.entity.chat.ChatMessage;
import com.ssafy.db.entity.chat.ChatRoom;
import com.ssafy.db.entity.chat.ChatRoomJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ChatSubController {

    @Autowired
    private ChatService chatService;

    private final SimpMessagingTemplate template;


    @Autowired
    public ChatSubController(SimpMessagingTemplate template){
        this.template = template;
    }

    // 채팅방 생성 요청
    @PostMapping(value="/chatroom")
    public ChatRoom createChatRoom(@RequestBody String name){
        return chatService.createChatRoom(name);
    }

    // 특정 유저의 전체 채팅방 목록 조회
    @GetMapping(value="/chatroom")
    public ResponseEntity getAllChatRoomsByUserId(@CookieValue("userId") Cookie userIdCookie){
        String userId = userIdCookie.getValue();
        System.out.println("userId : " + userId);

        // 1. 현재 요청한 회원의 userId에 맞는 roomId 리스트 받아오기
        List<ChatRoomJoin> joinedRoomList =  chatService.getChatroomListByUser(userId);

        // 2. roomId에 해당하는 CharRoom 리스트 반환하기
        List<ChatRoom> chatRoomList = new ArrayList<>();
        for(int i = 0; i < joinedRoomList.size(); i++){
            chatRoomList.add(joinedRoomList.get(i).getRoomId());
        }
        System.out.println("검색된 채팅 방의 개수" + chatRoomList.size());
        return ResponseEntity.ok(ChatRoomListGetRes.of(200, "Success", chatRoomList));
    }

    // 특정 채팅방의 채팅목록 조회
    @GetMapping(value="/chatroom/{roomId}/messages")
    public ResponseEntity getAllChatMessages(@CookieValue("userId") Cookie userIdCookie, @PathVariable("roomId") Long roomId){
        List<ChatMessage> messageList = chatService.getChatMessageList(chatService.getChatRoomInfo(roomId));
        List<ChatMessageGetRes> list = new ArrayList<>();
        for (int i = 0; i < messageList.size(); i++){
            list.add(ChatMessageGetRes.copy(messageList.get(i)));
        }
        System.out.println("검색된 채팅 로그의 개수" + list.size());
        return ResponseEntity.ok(ChatMessageListGetRes.of(200, "Success", list));
    }
}
