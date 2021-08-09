package com.ssafy.api.service;

import com.ssafy.api.request.ChatMessagePostReq;
import com.ssafy.db.entity.chat.ChatMessage;
import com.ssafy.db.entity.chat.ChatRoom;
import com.ssafy.db.entity.chat.ChatRoomJoin;

import java.util.List;

public interface ChatService {

    /* 채팅방 생성 */
    ChatRoom createChatRoom(String name);

    /* 회원의 채팅방 Join 목록 가져오기 */
    List<ChatRoomJoin> getChatroomListByUser(String userId);

    /* 회원이 Join 중인 채팅방의 정보 가져오기 */
    ChatRoom getChatRoomInfo(Long roomId);

    /* 회원이 메세지를 보내면 DB에 저장하기 */
    ChatMessage saveChatMessage(ChatMessagePostReq message);

    /* 회원이 Join 중인 채팅방의 메세지 기록 가져오기 */
    List<ChatMessage> getChatMessageList(ChatRoom roomId);
}
