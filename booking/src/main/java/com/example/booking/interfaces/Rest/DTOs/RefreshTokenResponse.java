package com.example.booking.interfaces.Rest.DTOs;

public record RefreshTokenResponse(
        String accessToken,
        String refreshToken
) {}
