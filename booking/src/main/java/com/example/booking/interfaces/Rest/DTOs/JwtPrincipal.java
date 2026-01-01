package com.example.booking.interfaces.Rest.DTOs;

import java.util.UUID;

import com.example.booking.domain.models.Role;


public record JwtPrincipal (
    UUID uuid,
    Role role
){}
