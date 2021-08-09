package com.ssafy.api.service;

import com.ssafy.api.request.ChatMessagePostReq;
import com.ssafy.db.entity.chat.ChatMessage;
import com.ssafy.db.entity.chat.ChatRoom;
import com.ssafy.db.entity.chat.ChatRoomJoin;
import com.ssafy.db.repository.chat.ChatMessageRepository;
import com.ssafy.db.repository.chat.ChatRoomJoinRepository;
import com.ssafy.db.repository.chat.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("chatService")
public class ChatServiceImpl implements ChatService{

    @Autowired
    ChatRoomJoinRepository chatRoomJoinRepository;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    ChatMessageRepository chatMessageRepository;

    @Override
    public ChatRoom createChatRoom(String name) {
        ChatRoom newChatRoom = new ChatRoom();
        newChatRoom.setName(name);
        chatRoomRepository.save(newChatRoom);
        return newChatRoom;
    }

    @Override
    public List<ChatRoomJoin> getChatroomListByUser(String userId) {
        Optional<List<ChatRoomJoin>> roomList = chatRoomJoinRepository.findChatRoomsJoinByUserId(userId);
        if(roomList.isPresent())
            return roomList.get();
        return null;
    }

    @Override
    public ChatRoom getChatRoomInfo(Long roomId) {
        Optional<ChatRoom> chatRoom = chatRoomRepository.findById(roomId);
        if(chatRoom.isPresent())
            return chatRoom.get();
        return null;
    }

    @Override
    public ChatMessage saveChatMessage(ChatMessagePostReq message) {
        ChatMessage saveMessage = new ChatMessage();
        saveMessage.setUserId(message.getUserId());
        saveMessage.setRoomId(getChatRoomInfo(message.getRoomId()));
        saveMessage.setMessage(message.getChatMessage());
        chatMessageRepository.save(saveMessage);
        return saveMessage;
    }

    @Override
    public List<ChatMessage> getChatMessageList(ChatRoom roomId) {
        Optional<List<ChatMessage>> messageList = chatMessageRepository.findAllByRoomId(roomId);
        if(messageList.isPresent())
            return messageList.get();
        return null;
    }

}
