package com.example.booking.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.booking.infrastructure.persistence.Entities.UserEntity;

public interface JpaUserRepository extends JpaRepository<UserEntity, UUID>{
    
    Optional<UserEntity> findByEmail(String email);
}
