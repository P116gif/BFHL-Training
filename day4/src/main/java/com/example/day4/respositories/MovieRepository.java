package com.example.day4.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.day4.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    
}
