package com.example.booking.application.Register;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.booking.domain.models.Role;
import com.example.booking.domain.models.User;
import com.example.booking.domain.repository.UserRepository;


@Service
public class RegisterUserUseCase {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordHasher;

    
    public RegisterUserUseCase (UserRepository userRepository, 
                                PasswordEncoder passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    public User registerUser(String username, String rawPassword, 
                                    String email, String phone) {
       
            userRepository.findByEmail(email).ifPresent(user -> {
                throw new IllegalArgumentException("Email already in registered");
            });

            String hashedPassword = passwordHasher.encode(rawPassword);

            User user = new User(
                    username,
                    email,
                    phone,
                    hashedPassword,
                    Role.USER,
                    true
            );

            return userRepository.save(user);
    }
}