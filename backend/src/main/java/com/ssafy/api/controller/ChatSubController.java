package com.ssafy.api.controller;

import com.ssafy.db.entity.chat.ChatMessage;
import com.ssafy.db.entity.chat.ChatRoom;
import com.ssafy.db.repository.chat.ChatMessageRepository;
import com.ssafy.db.repository.chat.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ChatSubController {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

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

    // 특정 채팅방의 채팅목록 조회
    @GetMapping(value="/chatroom/{roomId}")
    public ResponseEntity getAllChatMessages(@PathVariable("roomId") Long roomId){
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElse(null);

        if(chatRoom != null)
            return new ResponseEntity(chatRoom.getMessages(), HttpStatus.OK);
        return new ResponseEntity("채팅방을 찾지 못하였습니다.", HttpStatus.NOT_FOUND);
    }
}
