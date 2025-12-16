package com.example.day4.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.day4.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    
}
