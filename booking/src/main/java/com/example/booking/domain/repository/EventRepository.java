package com.example.booking.domain.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.booking.infrastructure.persistence.Entities.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

@Query("""
    SELECT DISTINCT e FROM EventEntity e
    JOIN e.shows s
    JOIN s.venue v
    WHERE (cast(:city as string) IS NULL OR v.city = :city)
      AND (cast(:genre as string) IS NULL OR e.genre = :genre)
      AND (cast(:language as string) IS NULL OR e.language = :language)
      AND (cast(:date as localdate) IS NULL OR FUNCTION('DATE', s.showTime) = :date)
""")
List<EventEntity> searchEvents(
        @Param("city") String city,
        @Param("genre") String genre,
        @Param("language") String language,
        @Param("date") LocalDate date
);
}