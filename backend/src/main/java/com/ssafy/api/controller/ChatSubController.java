package com.ssafy.api.controller;

import com.ssafy.db.entity.chat.ChatMessage;
import com.ssafy.db.entity.chat.ChatRoom;
import com.ssafy.db.repository.chat.ChatMessageRepository;
import com.ssafy.db.repository.chat.ChatRoomJoinRepository;
import com.ssafy.db.repository.chat.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatSubController {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ChatRoomJoinRepository chatRoomJoinRepository;

    private final SimpMessagingTemplate template;


    @Autowired
    public ChatSubController(SimpMessagingTemplate template){
        this.template = template;
    }

    // 채팅방 생성 요청
    @PostMapping(value="/chatroom")
    public ChatRoom createChatRoom(@RequestBody String name){
        ChatRoom newChatRoom = new ChatRoom();
        newChatRoom.setName(name);
        return chatRoomRepository.save(newChatRoom);
    }

    // 전체 채팅방 목록 조회
    @GetMapping(value="/chatroom")
    public List<ChatRoom> getAllChatRooms(){
        return chatRoomRepository.findAll();
    }

    // 특정 유저의 전체 채팅방 목록 조회
    @GetMapping(value="/chatroom/user/{userId}")
    public List<ChatRoom> getAllChatRoomsByUserId(@PathVariable("userId") Long userId){
        // 1. 특정 유저의 userId가 현재 요청하는 사람의 userId와 일치하는지 확인

        // 2. 맞다면 ChatRoomJoin 테이블에서 해당 userId를 가진사람의 roomId 리스트 받아오기

        // 3. roomId에 해당하는 CharRoom 리스트 반환하기
        return chatRoomRepository.findAll();
    }

    // 특정 채팅방의 채팅목록 조회
    @GetMapping(value="/chatroom/{roomId}")
    public ResponseEntity getAllChatMessages(@PathVariable("roomId") Long roomId){
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElse(null);

        if(chatRoom != null)
            return new ResponseEntity(chatRoom.getMessages(), HttpStatus.OK);
        return new ResponseEntity("채팅방을 찾지 못하였습니다.", HttpStatus.NOT_FOUND);
    }
}
