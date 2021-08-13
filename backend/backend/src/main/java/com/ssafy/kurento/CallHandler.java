package com.ssafy.kurento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
// TextWebSockerHandler 텍스트 WebSocker 요청을 처리하기 위해 구현
// 이 클래스의 중심 부분은 handleTextMessage 메소드
// 시그널링 프로토콜의 서버 부분 구현
public class CallHandler extends TextWebSocketHandler{

    private static final Logger log = LoggerFactory.getLogger(CallHandler.class);

    private static final Gson gson = new GsonBuilder().create();

//	@Autowired
//	private RoomManager roomManager;

    @Autowired
//	private UserRegistry registry;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
        final JsonObject jsonMessage = gson.fromJson(message.getPayload(), JsonObject.class);

//		final UserSession user = registry.getBySession(session);


    }
}