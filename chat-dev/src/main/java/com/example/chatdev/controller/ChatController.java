package com.example.chatdev.controller;

import com.example.chatdev.model.ChatMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
/**
 * 사용자를 추가하는 메서드와 메시지를 전송하는 메서드를 가진 Controller
 * Spring 에서 STOMP 메세징 작업에 접근하는 방식은 @MessageMapping 어노테이션을 사용하여 구현된 엔드포인트에 연결하는 것이다.
 * 정의된 엔드포인트로 접근된 메시징 요청은 내부 처리 후 @SendTo 어노테이션을 통해 정의된 "/topic/public" 대상의 모든 가입자들에게 전송된다.
 */
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
