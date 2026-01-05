package com.example.booking.interfaces.Rest.DTOs;

public record PasswordRequest(
    String currentPassword,
    String newPassword
) {}
