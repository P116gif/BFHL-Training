package com.example.day2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(
    @NotBlank(message = "Name cannot be empty") String name,
    @Email(message = "Invalid email format") String email
){}
