package com.tentalk.etc.util;

import com.tentalk.etc.dto.AuthUser;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        AuthUser user = (AuthUser) session.getAttributes().get("user");
        System.out.println("WebSocket 연결됨: " + user.nickname());
    }
}
