package com.ssafy.api.service;

import com.ssafy.api.request.ChatMessagePostReq;
import com.ssafy.api.response.ChatMessageGetRes;
import com.ssafy.db.entity.chat.ChatMessage;
import com.ssafy.db.entity.chat.ChatMessageRead;
import com.ssafy.db.entity.chat.ChatRoom;
import com.ssafy.db.entity.chat.ChatRoomJoin;
import com.ssafy.db.repository.chat.ChatMessageReadRepository;
import com.ssafy.db.repository.chat.ChatMessageRepository;
import com.ssafy.db.repository.chat.ChatRoomJoinRepository;
import com.ssafy.db.repository.chat.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    ChatMessageReadRepository chatMessageReadRepository;

    @Autowired
    UserService userService;

    @Override
    public ChatRoom createChatRoom(String name) {
        ChatRoom newChatRoom = new ChatRoom();
        newChatRoom.setName(name);
        chatRoomRepository.save(newChatRoom);
        return newChatRoom;
    }

    @Override
    public List<ChatRoomJoin> getChatroomListByUser(String userId) {
        Optional<List<ChatRoomJoin>> roomList = chatRoomJoinRepository.findChatRoomJoinsByUserId(userId);
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

        System.out.println("sendTimeAt : " + saveMessage.getSendTimeAt());

        chatMessageRepository.save(saveMessage);
        return saveMessage;
    }

    @Override
    public ChatMessageRead saveRead(ChatMessageRead read) {
        return chatMessageReadRepository.save(read);
    }

    @Override
    public List<ChatMessage> getChatMessageList(ChatRoom roomId, String userId) {
        // 채팅방에 입장했을 때, 이전 채팅 기록을 불러오는 메소드
        List<ChatMessage> messageList = chatMessageRepository.findAllByRoomId(roomId).orElse(null);
        // 이 때, 안읽은 메시지가 있다면 isRead 값을 true로 변경해준다
        chatMessageReadRepository.updateIsRead(roomId, userId);

        return messageList;
    }

    @Override
    public List<ChatMessageGetRes> getChatMessageListWithUserName(List<ChatMessage> messageList) {
        List<ChatMessageGetRes> list = new ArrayList<>();
        for (int i = 0; i < messageList.size(); i++) {
            list.add(ChatMessageGetRes.copy(messageList.get(i)));
            list.get(i).setUsername(userService.getUserName(messageList.get(i).getUserId()));
        }
        return list;
    }

    @Override
    public List<String> getUserNameList(ChatRoom roomId) {
        List<ChatRoomJoin> roomList = chatRoomJoinRepository.findChatRoomJoinsByRoomId(roomId).orElse(null);
        List<String> nameList = new ArrayList<>();
        for(int i = 0; i < roomList.size(); i++){
            nameList.add(userService.getUserName(roomList.get(i).getUserId()));
        }
        return nameList;
    }

    @Override
    public List<String> getUserIdList(ChatRoom roomId) {
        List<ChatRoomJoin> roomList = chatRoomJoinRepository.findChatRoomJoinsByRoomId(roomId).orElse(null);
        List<String> idList = new ArrayList<>();
        for(int i = 0; i < roomList.size(); i++){
            idList.add(roomList.get(i).getUserId());
        }
        return idList;
    }

    @Override
    public int getUnReadMessage(ChatRoom roomId, String userId) {
        Optional<List<ChatMessageRead>> list = chatMessageReadRepository.findChatMessageReadsByRoomIdAndUserId(roomId, userId);
        if(list.isPresent())
            return list.get().size();
        return 0;
    }

}
