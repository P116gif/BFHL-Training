package com.example.day4.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.day4.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
