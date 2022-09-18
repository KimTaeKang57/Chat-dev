package com.example.chatdev.model;

import java.awt.*;

/**
 * 클라이언트와 서버 간에 교환될 메시지 페이로드 입니다.
 * PayLoad : 헤더와 메타데이터를 제외한 실제 사용에 있어서 필요한 데이터
 */
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
