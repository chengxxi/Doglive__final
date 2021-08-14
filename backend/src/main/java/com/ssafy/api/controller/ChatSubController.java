package com.ssafy.api.controller;


import com.ssafy.api.response.ChatMessageGetRes;
import com.ssafy.api.response.ChatMessageListGetRes;
import com.ssafy.api.response.ChatRoomGetRes;
import com.ssafy.api.response.ChatRoomListGetRes;
import com.ssafy.api.service.ChatService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.chat.ChatMessage;

import com.ssafy.db.entity.chat.ChatRoom;
import com.ssafy.db.entity.chat.ChatRoomJoin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Api(value = "상담채팅 API", tags = {"Chat"})
@RestController
@RequestMapping("/api/v1/chatroom")
public class ChatSubController {

    @Autowired
    private ChatService chatService;

    private final SimpMessagingTemplate template;

    @Autowired
    public ChatSubController(SimpMessagingTemplate template){
        this.template = template;
    }

    @PostMapping(value="")
    @ApiOperation(value = "채팅방 생성 요청", notes = "상담신청 시, 공고자와 신청자이 상담할 수 있는 채팅방이 생성된다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity createChatRoom(@CookieValue("userId") Cookie userIdCookie, @RequestBody HashMap<String, Object> body){
        System.out.println(body);
        int id = (Integer)body.get("counseling_id");
        Long counseling_id = Long.valueOf(id);
        chatService.createChatRoom(counseling_id);
        return ResponseEntity.ok(BaseResponseBody.of(200, "채팅방이 생성되었습니다."));
    }

    @GetMapping(value="")
    @ApiOperation(value = "유저의 전체 채팅방 목록 조회", notes = "현재 로그인한 사용자의 채팅방 목록을 불러온다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity getAllChatRoomsByUserId(@CookieValue("userId") Cookie userIdCookie){
        String userId = userIdCookie.getValue();
        System.out.println("userId : " + userId);

        // 1. 현재 요청한 회원의 userId에 맞는 roomId 리스트 받아오기
        List<ChatRoomJoin> joinedRoomList =  chatService.getChatroomJoinListByUser(userId);
        // 2. roomId에 해당하는 ChatRoom 리스트 반환하기 + 해당 채팅방안에 있는 username도 추가
        List<ChatRoomGetRes> chatRoomList = chatService.getChatroomList(joinedRoomList, userId);

        System.out.println("검색된 채팅 방의 개수" + chatRoomList.size());
        return ResponseEntity.ok(ChatRoomListGetRes.of(200, "Success", chatRoomList));
    }

    @GetMapping(value="/{roomId}/messages/{page}")
    @ApiOperation(value = "채팅방의 채팅 목록 조회", notes = "선택한 채팅방에서 이전 채팅 로그들을 페이징 처리하여 불러온다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity getAllChatMessages(@CookieValue("userId") Cookie userIdCookie,
                                             @PathVariable("roomId") Long roomId,
                                             @PathVariable("page") int page){

        String userId = userIdCookie.getValue();
        List<ChatMessage> messageList = chatService.getChatMessageList(chatService.getChatRoomInfo(roomId), userId, page);
        List<ChatMessageGetRes> list = chatService.getChatMessageListWithUserName(messageList);
        System.out.println("검색된 채팅 로그의 개수" + list.size());
        return ResponseEntity.ok(ChatMessageListGetRes.of(200, "Success", list));
    }

    @DeleteMapping(value="/{roomId}")
    @ApiOperation(value = "채팅방 삭제", notes = "해당 채팅방을 삭제한다 (삭제 테스트용)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity deleteChatroom(@PathVariable("roomId") Long roomId){
        ChatRoom chatRoom = chatService.getChatRoomInfo(roomId);
        chatService.deleteChatRoom(chatRoom);

        return ResponseEntity.ok(BaseResponseBody.of(200, "채팅방이 삭제되었습니다."));
    }
}
