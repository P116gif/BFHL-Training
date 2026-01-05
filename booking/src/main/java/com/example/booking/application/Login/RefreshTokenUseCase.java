package com.example.booking.application.Login;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.booking.domain.models.Role;
import com.example.booking.interfaces.Login.JwtService;
import com.example.booking.interfaces.Login.RefreshTokenStore;
import com.example.booking.interfaces.Rest.DTOs.RefreshTokenResponse;


@Service
public class RefreshTokenUseCase {
    
    private final JwtService jwtService;
    private final RefreshTokenStore refreshTokens;
    
    @SuppressWarnings("unused")
    RefreshTokenUseCase(JwtService jwtService, RefreshTokenStore refreshTokens) {
        this.jwtService = jwtService;
        this.refreshTokens = refreshTokens;
    }

    public RefreshTokenResponse refresh(String token) {

        //validate user refresh token
        if(!jwtService.validateToken(token)) {
            throw new IllegalArgumentException("Invalid refresh token");
        }

        //extract userID and role
        UUID userId = jwtService.extractUserId(token);
        Role role = jwtService.extractUserRole(token);
        
        //validate token against redis
        if(!refreshTokens.isValid(userId, token)) {
            throw  new IllegalArgumentException("Refresh token not recognized");
        }

        //generate new tokens
        String newAccessToken = jwtService.generateAccessToken(userId, role);
        String newRefreshToken = jwtService.generateRefreshToken(userId, role);

        refreshTokens.store(userId, newRefreshToken);

        return new RefreshTokenResponse(newAccessToken, newRefreshToken);
    }

}
