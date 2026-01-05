package com.example.booking.interfaces.Rest.DTOs;

public record SeatCreationRequest(

    String seatNumber,
    Integer rowNumber
){}
