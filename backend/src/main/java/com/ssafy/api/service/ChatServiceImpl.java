package com.ssafy.api.service;

import com.ssafy.api.request.ChatMessagePostReq;
import com.ssafy.api.response.ChatMessageGetRes;
import com.ssafy.api.response.ChatRoomGetRes;
import com.ssafy.db.entity.auth.CounselingHistory;
import com.ssafy.db.entity.chat.ChatMessage;
import com.ssafy.db.entity.chat.ChatMessageRead;
import com.ssafy.db.entity.chat.ChatRoom;
import com.ssafy.db.entity.chat.ChatRoomJoin;
import com.ssafy.db.repository.auth.CounselingHistoryRepository;
import com.ssafy.db.repository.chat.ChatMessageReadRepository;
import com.ssafy.db.repository.chat.ChatMessageRepository;
import com.ssafy.db.repository.chat.ChatRoomJoinRepository;
import com.ssafy.db.repository.chat.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    CounselingHistoryRepository counselingHistoryRepository;

    @Autowired
    UserService userService;

    @Override
    @Transactional
    public ChatRoom createChatRoom(Long counseling_id) {
        // 1. 채팅방을 만들고 Room ID를 반환받음
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setCounselingId(counseling_id);
        chatRoomRepository.save(chatRoom);

        // 2. chat_room_join에 방금 만든 채팅방에 대해서
        CounselingHistory counselingHistory = userService.getCounselingById(counseling_id);
        System.out.println(counselingHistory);

        saveChatRoomJoin(chatRoom, counselingHistory.getWriter()); // 글 작성자
        saveChatRoomJoin(chatRoom, counselingHistory.getApplicantId().getUserId().getId()); // 상담 신청자

        return chatRoom;
    }

    @Override
    public ChatRoomJoin saveChatRoomJoin(ChatRoom roomId, String userId) {
        ChatRoomJoin join = new ChatRoomJoin();
        join.setRoomId(roomId);
        join.setUserId(userId);
        chatRoomJoinRepository.save(join);
        return join;
    }

    @Override
    public List<ChatRoomJoin> getChatroomJoinListByUser(String userId) {
        Optional<List<ChatRoomJoin>> roomList = chatRoomJoinRepository.findChatRoomJoinsByUserId(userId);
        if(roomList.isPresent())
            return roomList.get();
        return null;
    }

    @Override
    public List<ChatRoomGetRes> getChatroomList(List<ChatRoomJoin> joinedRoomList, String userId) {
        List<ChatRoomGetRes> chatRoomList = new ArrayList<>();

        for(int i = 0; i < joinedRoomList.size(); i++){
            ChatRoomGetRes chatRoom = new ChatRoomGetRes();
            ChatRoom room = joinedRoomList.get(i).getRoomId(); // 채팅방 목록의 roomId
            chatRoom.setChatRoom(room);

            CounselingHistory history = userService.getCounselingById(room.getCounselingId());
            if(history == null) // 해당 상담이 이미 삭제되었다면 채팅방 목록에 담지 않는다.
                continue;

            chatRoom.setCounselingHistory(userService.getCounselingById(room.getCounselingId()));
            chatRoom.setUserNameList(getUserNameList(room));
            chatRoom.setUnReadCount(getUnReadMessage(room, userId));
            chatRoomList.add(chatRoom);
            System.out.println("안읽은 메세지 개수" + chatRoom.getUnReadCount());
        }
        return chatRoomList;
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
    public void updateRead(ChatRoom roomId, String userId) {chatMessageReadRepository.updateIsRead(roomId, userId);}

    @Override
    public List<ChatMessage> getChatMessageList(ChatRoom roomId, String userId, int page) {
        // 채팅방에 입장했을 때, 이전 채팅 기록을 불러오는 메소드 (최신순 페이징 처리)
        PageRequest pageRequest = PageRequest.of(page,30, Sort.Direction.DESC, "id");
        Page<ChatMessage> messageList = chatMessageRepository.findAllByRoomId(roomId, pageRequest).orElse(null);
        System.out.println("Total Pages : " + messageList.getTotalPages());
        System.out.println("Total Count : " + messageList.getTotalElements());
        System.out.println("Next : " + messageList.nextPageable());

        // 이 때, 안읽은 메시지가 있다면 isRead 값을 true로 변경해준다
        chatMessageReadRepository.updateIsRead(roomId, userId);

        return messageList.getContent();
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

    @Override
    public void deleteChatRoom(ChatRoom roomId) {
        chatMessageReadRepository.deleteChatMessageReadByRoomId(roomId);
        chatMessageRepository.deleteChatMessageByRoomId(roomId);
        chatRoomJoinRepository.deleteChatRoomJoinByRoomId(roomId);
        chatRoomRepository.delete(roomId);
    }

    @Override
    public ChatRoom getChatRoomInfoByCounselingId(Long counselingId) {
        Optional<ChatRoom> room = chatRoomRepository.findByCounselingId(counselingId);
        if(room.isPresent())
            return room.get();
        return null;
    }

    @Override
    public ChatRoomGetRes getChatRoomGetRes(ChatRoom roomId, String userId) {
        ChatRoomGetRes chatRoom = new ChatRoomGetRes();
        chatRoom.setChatRoom(roomId);
        chatRoom.setCounselingHistory(userService.getCounselingById(roomId.getCounselingId()));
        chatRoom.setUserNameList(getUserNameList(roomId));
        chatRoom.setUnReadCount(getUnReadMessage(roomId, userId));
        return chatRoom;
    }

}
