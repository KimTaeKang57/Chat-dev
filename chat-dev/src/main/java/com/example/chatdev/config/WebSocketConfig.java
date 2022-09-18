package com.example.chatdev.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 클라이언트가 웹 소켓 서버에 연결하는 데 사용할 웹 소켓 endpoint 를 등록합니다.
     * SockJS 는 웹소켓을 지원하지 않는 브라우저에 대한 대체 옵션을 활성화 하는데 사용됩니다. (github readme 표 참조)
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    /**
     * 한 클라이언트에서 다른 클라이언트로 메세지를 라우팅하는 데 사용할 메시지 브로커를 구성합니다.
     * /app 으로 시작하는 메시지가 메시치 처리 메서드로 라우팅되어야 한다 정의한다.
     * 목적지가 /topic 으로 시작하는 메시지가 메시지 브로커로 라우팅되어야 한다.
     */
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.setApplicationDestinationPrefixes("/app");
//
//        // Use this for enabling a Full featured broker like RabbitMQ
//        registry.enableStompBrokerRelay("/topic")
//                .setRelayHost("localhost")
//                .setRelayPort(61613)
//                .setClientLogin("guest")
//                .setClientPasscode("guest");
//    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}
