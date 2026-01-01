package com.example.booking.interfaces.Rest.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.application.Login.LoginUseCase;
import com.example.booking.interfaces.Rest.DTOs.JwtPrincipal;
import com.example.booking.interfaces.Rest.DTOs.LoginRequest;
import com.example.booking.interfaces.Rest.DTOs.LoginResponse;


@RestController
@RequestMapping("/api/auth")
public class LoginController {
    
    private final LoginUseCase loginUseCase;

    public LoginController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){

        LoginResponse response = loginUseCase.login(
            loginRequest.email(), 
            loginRequest.password());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal JwtPrincipal jwtPrincipal){

        loginUseCase.logout(jwtPrincipal.uuid());
        return ResponseEntity.ok().build();
    }

}
