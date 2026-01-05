package com.example.booking.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.booking.infrastructure.persistence.Entities.AuditoriumEntity;

public interface AuditoriumRepository extends JpaRepository<AuditoriumEntity, Long>{}
