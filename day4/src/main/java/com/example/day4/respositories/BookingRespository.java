package com.example.day4.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.day4.entities.Booking;

public interface BookingRespository extends JpaRepository<Booking, Long> {
    
}
