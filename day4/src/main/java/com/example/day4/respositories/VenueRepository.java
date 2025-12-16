package com.example.day4.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.day4.entities.Venue;

public interface VenueRepository extends JpaRepository<Venue, Long> {
    
}
