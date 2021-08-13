package com.ssafy.api.service;

import com.ssafy.api.request.ChatMessagePostReq;
import com.ssafy.api.response.ChatMessageGetRes;
import com.ssafy.db.entity.chat.ChatMessage;
import com.ssafy.db.entity.chat.ChatMessageRead;
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

    /* 메세지 읽음 여부 저장하기 */
    ChatMessageRead saveRead(ChatMessageRead read);

    /* 회원이 Join 중인 채팅방의 메세지 기록 가져오기 */
    List<ChatMessage> getChatMessageList(ChatRoom roomId, String userId, int page);

    /* 회원이 Join 중인 채팅방의 메세지 기록 가져오기 (userName을 담는 로직 추가)*/
    List<ChatMessageGetRes> getChatMessageListWithUserName(List<ChatMessage> messageList);

    /* 채팅방 안에 있는 회원의 닉네임 가져오기 */
    List<String> getUserNameList(ChatRoom roomId);

    /* 채팅방 안에 있는 회원의 id 가져오기 */
    List<String> getUserIdList(ChatRoom roomId);

    /* 채팅방에서 읽지않은 메세지의 개수 가져오기 */
    int getUnReadMessage(ChatRoom roomId, String userId);

    /* 채팅을 전송할 때 메시지 읽음 여부 저장하기 -> 보내는 사람은 true(1), 받는 사람은 false(0)*/

}
