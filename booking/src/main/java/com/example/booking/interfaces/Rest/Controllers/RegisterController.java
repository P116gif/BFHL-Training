package com.example.booking.interfaces.Rest.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.application.Register.RegisterUserUseCase;
import com.example.booking.interfaces.Rest.DTOs.RegisterRequest;

@RestController
@RequestMapping("/api/auth")
public class RegisterController {
    
    private final RegisterUserUseCase registerUserUseCase;

    public RegisterController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest registerRequest){

        registerUserUseCase.registerUser(
            registerRequest.name(), 
            registerRequest.password(), 
            registerRequest.email(), 
            registerRequest.phone());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
