package com.example.booking.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.booking.domain.models.SeatCategory;
import com.example.booking.infrastructure.persistence.Entities.ShowSeatPriceEntity;

public interface ShowSeatPriceRepository
        extends JpaRepository<ShowSeatPriceEntity, Long> {

    Optional<ShowSeatPriceEntity> findByShowIdAndCategory(
            Long showId, SeatCategory category);
}

