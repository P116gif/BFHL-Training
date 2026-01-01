package com.example.booking.domain.models;

import java.util.UUID;

public class User {
    
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String passwordHash;
    private Role role;
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
    public Boolean isActive() {
        return isActive;
    }
}
