package com.example.day7.Others;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class Argon {
    
    public static void main(String[] args) {

                                                                        // saltL, hashL, parallelism, memory, iterations
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 65536, 4);

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
