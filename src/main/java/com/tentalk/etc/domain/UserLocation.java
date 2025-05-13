package com.tentalk.etc.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "user_locations")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLocation {

    @Id
    private String userId;

    private String nickname;

    private double latitude;

    private double longitude;

    private LocalDateTime updatedAt;

    public void updateLocation(double lat, double lon) {
        this.latitude = lat;
        this.longitude = lon;
        this.updatedAt = LocalDateTime.now();
    }
}