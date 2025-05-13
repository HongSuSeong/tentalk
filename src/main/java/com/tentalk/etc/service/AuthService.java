package com.tentalk.etc.service;


import com.tentalk.etc.domain.User;
import com.tentalk.etc.dto.AuthRequest;
import com.tentalk.etc.dto.AuthResponse;
import com.tentalk.etc.repository.UserRepository;
import com.tentalk.etc.util.JwtProvider;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public AuthResponse loginOrRegister(AuthRequest request) {
        User user = userRepository.findByDeviceId(request.deviceId())
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setDeviceId(request.deviceId());
                    newUser.setNickname(request.nickname());
                    return userRepository.save(newUser);
                });

        user.setLastLoginAt(LocalDateTime.now());
        userRepository.save(user);

        String token = jwtProvider.generateToken(user);
        return new AuthResponse(token, user.getNickname(), user.getId());
    }
}