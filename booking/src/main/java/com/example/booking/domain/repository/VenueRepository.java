package com.example.booking.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.booking.infrastructure.persistence.Entities.VenueEntity;

public interface VenueRepository extends JpaRepository<VenueEntity, Long> {}
