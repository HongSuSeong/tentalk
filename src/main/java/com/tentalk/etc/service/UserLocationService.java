package com.tentalk.etc.service;

import com.tentalk.etc.domain.UserLocation;
import com.tentalk.etc.dto.UserLocationResponse;
import com.tentalk.etc.repository.UserLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLocationService {

    private final UserLocationRepository userLocationRepository;

    public void saveOrUpdateLocation(String userId, String nickname, double lat, double lon) {
        UserLocation location = userLocationRepository.findById(userId)
                .map(l -> {
                    l.updateLocation(lat, lon);
                    return l;
                })
                .orElse(UserLocation.builder()
                        .userId(userId)
                        .nickname(nickname)
                        .latitude(lat)
                        .longitude(lon)
                        .updatedAt(LocalDateTime.now())
                        .build()
                );
        userLocationRepository.save(location);
    }

    public List<UserLocationResponse> findUsersWithin10Km(double lat, double lon) {
        return userLocationRepository.findUsersWithinRadius(lat, lon, 10.0)
                .stream()
                .map(loc -> new UserLocationResponse(
                        loc.getUserId(), loc.getNickname(), loc.getLatitude(), loc.getLongitude()))
                .toList();
    }
}
