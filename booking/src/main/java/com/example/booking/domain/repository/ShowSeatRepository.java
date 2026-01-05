package com.example.booking.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.example.booking.domain.models.SeatStatus;
import com.example.booking.infrastructure.persistence.Entities.ShowSeatEntity;

import jakarta.persistence.LockModeType;



public interface ShowSeatRepository extends JpaRepository<ShowSeatEntity, Long> {
    
    List<ShowSeatEntity> findByShowId(Long showId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<ShowSeatEntity> findByShowIdAndSeatId(Long showId, Long seatId);

    List<ShowSeatEntity> findBySeatStatusAndLockedAtBefore(SeatStatus seatStatus, LocalDateTime cutoff);

    @Query("""
        SELECT ss.seat.seatCategory, COUNT(ss)
        FROM ShowSeatEntity ss
        WHERE ss.show.id = :showId
        AND ss.seatStatus = 'AVAILABLE'
        GROUP BY ss.seat.seatCategory
    """)
    List<Object[]> countAvailableSeatsByCategory(Long showId);
}   
