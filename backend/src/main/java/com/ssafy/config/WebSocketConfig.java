package com.ssafy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Profile("stomp")
@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // Web Socket 통신을 연결할 End-Point를 지정해주는 메소드
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/chat-server").setAllowedOrigins("*").withSockJS(); // WebSocket을 지원하지 않는 브라우저에는 SockJS 지원
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/pub"); // 메시지 요청을 처리할 주소의 prefix (웹 소켓용)
        registry.enableSimpleBroker("/sub"); // 채팅방 구독 요청을 처리할 주소의 prefix (웹 소켓용)
    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration){
        registration.interceptors(new MyChannelInterceptor());
    }
}
