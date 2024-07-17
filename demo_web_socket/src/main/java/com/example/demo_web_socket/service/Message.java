package com.example.demo_web_socket.service;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    private MsgType type;
    private String content;
    private String sender;
}
