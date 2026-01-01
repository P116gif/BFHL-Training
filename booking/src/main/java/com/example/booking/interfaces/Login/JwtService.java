package com.example.booking.interfaces.Login;

import java.util.UUID;

import com.example.booking.domain.models.Role;

public interface JwtService {

    String generateAccessToken(UUID userId, Role role);
    String generateRefreshToken(UUID userId, Role role);
    boolean validateToken(String token);
    UUID extractUserId(String token);
    Role extractUserRole(String token);
}
