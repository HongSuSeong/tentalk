package com.tentalk.etc.config;

import com.tentalk.etc.util.JwtHandshakeInterceptor;
import com.tentalk.etc.util.JwtProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final JwtProvider jwtProvider;

    public WebSocketConfig(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); // 메시지 송신 채널
        registry.setApplicationDestinationPrefixes("/app"); // 메시지 수신 경로 prefix
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .addInterceptors(new JwtHandshakeInterceptor(jwtProvider))  // ✅ 인터셉터 추가
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}

