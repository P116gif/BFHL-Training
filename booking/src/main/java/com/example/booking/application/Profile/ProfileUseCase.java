package com.example.booking.application.Profile;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.booking.domain.repository.UserRepository;


@Service
public class ProfileUseCase {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileUseCase(UserRepository userRepository, 
                PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void updateProfileContactInfo(UUID userId, 
                String name, String email, String phone) {

        //fetch the user
        var user = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));

        //update contact info
        user.updateContactInfo(name, email, phone);

        //save the updated user
        userRepository.save(user);
    }

    public void changePassword(UUID userId, 
                String currentRawPassword, String newRawPassword) {

        //fetch the user
        var user = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));

        //verify current password
        if(!passwordEncoder.matches(currentRawPassword, user.getPasswordHash())){
            throw new IllegalArgumentException("Current password is incorrect");
        }

        //encode new password
        String newPasswordHash = passwordEncoder.encode(newRawPassword);

        //update password
        user.updatePassword(newPasswordHash);

        //save the updated user
        userRepository.save(user);
    }
}
