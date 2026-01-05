package com.example.booking.interfaces.Rest.Controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.application.Profile.ProfileUseCase;
import com.example.booking.interfaces.Rest.DTOs.JwtPrincipal;
import com.example.booking.interfaces.Rest.DTOs.PasswordRequest;
import com.example.booking.interfaces.Rest.DTOs.ProfileRequest;



@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    
    private final ProfileUseCase profileUseCase;

    public ProfileController(ProfileUseCase profileUseCase) {
        this.profileUseCase = profileUseCase;
    }

    @PostMapping("/updateContactInfo")
    public void updateContactInfo(@RequestBody ProfileRequest profileRequest, @AuthenticationPrincipal JwtPrincipal jwtPrincipal){
        
        profileUseCase.updateProfileContactInfo(
            jwtPrincipal.uuid(),  
            profileRequest.name(),
            profileRequest.email(),
            profileRequest.phone()
        );
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody PasswordRequest passwordRequest, @AuthenticationPrincipal JwtPrincipal jwtPrincipal){ {
        
        try{
                profileUseCase.changePassword(
                        jwtPrincipal.uuid(), 
                        passwordRequest.currentPassword(),
                        passwordRequest.newPassword()
                    );
        }
        catch(Exception e){
            return e.getMessage();
        }

        return "Password changed successfully";
    }
    
    }
}
