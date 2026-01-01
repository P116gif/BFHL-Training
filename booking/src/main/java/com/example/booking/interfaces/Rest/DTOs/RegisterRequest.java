package com.example.booking.interfaces.Rest.DTOs;

public record RegisterRequest(
        String name,
        String email,
        String phone,
        String password
) {}
