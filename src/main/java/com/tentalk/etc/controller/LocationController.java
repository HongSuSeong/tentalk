package com.tentalk.etc.controller;

import com.tentalk.etc.dto.AuthUser;
import com.tentalk.etc.dto.LocationRequest;
import com.tentalk.etc.dto.UserLocationResponse;
import com.tentalk.etc.service.UserLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
@RequiredArgsConstructor
public class LocationController {

    private final UserLocationService locationService;

    @PostMapping
    public ResponseEntity<Void> saveLocation(@RequestBody LocationRequest request,
                                             Authentication authentication) {
        AuthUser user = (AuthUser) authentication.getPrincipal();
        locationService.saveOrUpdateLocation(user.userId(), user.nickname(),
                request.latitude(), request.longitude());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<UserLocationResponse>> getNearbyUsers(
            @RequestParam double latitude,
            @RequestParam double longitude) {
        List<UserLocationResponse> users = locationService.findUsersWithin10Km(latitude, longitude);
        return ResponseEntity.ok(users);
    }
}
