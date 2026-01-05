package com.example.booking.domain.models;

import java.util.UUID;

public class User {

    
    private final UUID id;
    private String name;
    private String email;
    private String phone;
    private String passwordHash;
    private final Role role;
    private Boolean isActive;

    
    public User(String name, String email, String phone, 
                        String passwordHash, Role role, Boolean isActive) {

        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.passwordHash = passwordHash;
        this.role = role;
        this.isActive = isActive;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void activate() {
        this.isActive = true;
    }

    public void updateContactInfo(String name, String email, String phone) {
        // Assuming email and phone are validated before calling this method
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void updatePassword(String newPasswordHash) {
        // Assuming password strength is validated before calling this method
        this.passwordHash = newPasswordHash;
    }

    // Getters
    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public Role getRole() {
        return role;
    }
    public Boolean getIsActive() {
        return isActive;
    }
}
