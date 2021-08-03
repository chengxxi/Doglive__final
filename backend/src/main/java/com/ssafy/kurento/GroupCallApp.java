package com.ssafy.kurento;

import org.kurento.client.KurentoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
// KurentoClient는 이 클래스에서 Spring Bean으로 인스턴스화되고,
// 이 빈은 애플리케이션 미디어 기능을 추가하는 데 사용되는 Kurento Media Server의 위치를 지정해야 함
// 현재는 포트 TCP 8888을 수신하는 localhost에 있다고 가정
@EnableWebSocket
public class GroupCallApp implements WebSocketConfigurer{

//	  @Bean
//	  public UserRegistry registry() {
//	    return new UserRegistry();
//	  }
//
//	  @Bean
//	  public RoomManager roomManager() {
//	    return new RoomManager();
//	  }

    public CallHandler groupCallHandler() {
        return new CallHandler();
    }

    @Bean
    public KurentoClient kurentoClient() {
        return KurentoClient.create();
    }

    @Bean
    public ServletServerContainerFactoryBean createServletServerContainerFactoryBean() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(32768);
        return container;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(GroupCallApp.class, args);
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(groupCallHandler(), "/groupcall");
    }
}