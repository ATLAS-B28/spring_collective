package com.example.web.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatMessage {

    private String content;
    private String sender;
    private MessageType type;

    public enum MessageType {
        CHAT, LEAVE, JOIN
    }
}
