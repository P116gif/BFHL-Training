package com.example.booking.interfaces.Rest.DTOs;

public record LoginResponse (
    String accessToken,
    String refreshToken
) {}
