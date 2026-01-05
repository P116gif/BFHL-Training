package com.example.booking.interfaces.Login;

import java.util.UUID;

public interface RefreshTokenStore {
    
    void store(UUID userId, String refreshToken);
    boolean isValid(UUID userId, String refreshToken);
    void revoke(UUID userId);
}
