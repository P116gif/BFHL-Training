package com.example.booking.interfaces.Rest.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.application.Login.RefreshTokenUseCase;
import com.example.booking.interfaces.Rest.DTOs.RefreshTokenRequest;
import com.example.booking.interfaces.Rest.DTOs.RefreshTokenResponse;

@RestController
@RequestMapping("/api/auth")
public class RefreshTokenController {

    private final RefreshTokenUseCase refreshTokenUseCase;
    
    public RefreshTokenController(RefreshTokenUseCase refreshTokenUseCase) {
        this.refreshTokenUseCase = refreshTokenUseCase;
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refresh(
        @RequestBody RefreshTokenRequest request){

        RefreshTokenResponse response = refreshTokenUseCase.refresh(
            request.refreshToken());

        return ResponseEntity.ok(response);
    }

}
