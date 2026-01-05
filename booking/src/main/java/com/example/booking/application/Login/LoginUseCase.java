package com.example.booking.application.Login;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.booking.domain.models.Role;
import com.example.booking.domain.models.User;
import com.example.booking.domain.repository.UserRepository;
import com.example.booking.interfaces.Login.JwtService;
import com.example.booking.interfaces.Login.RefreshTokenStore;
import com.example.booking.interfaces.Rest.DTOs.LoginResponse;


@Service
public class LoginUseCase {
    
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RefreshTokenStore refreshTokens;
    private final PasswordEncoder passwordEncoder;

    public LoginUseCase(UserRepository userRepository, 
                JwtService jwtService, RefreshTokenStore refreshTokens, 
                PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.refreshTokens = refreshTokens;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(String email, String rawPassword){
        
        //fetch the user
        User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        //getUserId and role
        UUID userId = user.getId();
        Role role = user.getRole();

        //check if user is active
        if(!user.getIsActive()){
            throw new IllegalStateException("User account is inactive");
        }

        //verify password
        if(!passwordEncoder.matches(rawPassword, user.getPasswordHash())){
            throw new IllegalArgumentException("Invalid email or password");
        }

        //generate tokens
        String accessToken = jwtService.generateAccessToken(userId, role);
        String refreshToken = jwtService.generateRefreshToken(userId, role);

        //store refresh tokens
        refreshTokens.store(user.getId(), refreshToken);

        //return response
        return new LoginResponse(accessToken, refreshToken);
    }

    public void logout(UUID userId) {
        refreshTokens.revoke(userId);
    }
}
