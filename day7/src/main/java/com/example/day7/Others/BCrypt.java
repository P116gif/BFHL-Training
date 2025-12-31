package com.example.day7.Others;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCrypt {
    
    public static void main(String[] args) {
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String rawPassword = "password";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        System.out.println("Encoded Password: " + encodedPassword);

        boolean isPasswordMatch = passwordEncoder.matches(rawPassword, encodedPassword);

        if(isPasswordMatch) {
            System.out.println("Password matches!");
        } else {
            System.out.println("Password does not match.");
        }
        
    }
}
