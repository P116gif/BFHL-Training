package com.example.booking.interfaces.Rest.DTOs;

public record ProfileRequest(
    String name,
    String email,
    String phone
) {}
