package com.tentalk.etc.controller;

import com.tentalk.etc.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    @MessageMapping("/chat/send")
    @SendTo("/topic/public")
    public ChatMessage send(ChatMessage message) {
        return message;
    }
}
