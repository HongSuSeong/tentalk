package com.tentalk.etc.repository;

import com.tentalk.etc.domain.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserLocationRepository extends JpaRepository<UserLocation, String> {

    @Query("""
        SELECT u FROM UserLocation u
        WHERE (
            6371 * acos(
                cos(radians(:latitude)) *
                cos(radians(u.latitude)) *
                cos(radians(u.longitude) - radians(:longitude)) +
                sin(radians(:latitude)) *
                sin(radians(u.latitude))
            )
        ) <= :radius
    """)
    List<UserLocation> findUsersWithinRadius(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("radius") double radiusKm
    );
}