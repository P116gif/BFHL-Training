package com.example.booking.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.booking.infrastructure.persistence.Entities.ShowEntity;


public interface ShowRepository extends JpaRepository<ShowEntity, Long> {

     @Query("""
        SELECT s FROM ShowEntity s
        JOIN FETCH s.venue v
        JOIN FETCH s.auditorium a
        WHERE s.event.id = :eventId
          AND (:date IS NULL OR DATE(s.showTime) = :date)
        ORDER BY s.showTime
    """)
    List<ShowEntity> findShowsForEvent(
            @Param("eventId") Long eventId,
            @Param("date") LocalDate date
    );
}
