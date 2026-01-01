package com.example.booking.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.example.booking.domain.models.User;

public interface UserRepository {
    
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
    User save(User user);
}
