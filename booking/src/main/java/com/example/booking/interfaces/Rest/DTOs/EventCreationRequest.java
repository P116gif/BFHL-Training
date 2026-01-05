package com.example.booking.interfaces.Rest.DTOs;

public record EventCreationRequest(
    String title,
    String language,
    String genre,
    Integer duration,
    String rating
){}