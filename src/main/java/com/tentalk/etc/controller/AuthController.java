package com.tentalk.etc.controller;

import com.tentalk.etc.dto.AuthRequest;
import com.tentalk.etc.dto.AuthResponse;
import com.tentalk.etc.dto.LoginRequest;
import com.tentalk.etc.service.AuthService;
import com.tentalk.etc.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        // 실제 서비스에서는 이 부분에서 사용자 유효성 검사 필요
        String token = jwtProvider.createToken(request.getDeviceId(), request.getNickname());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}