package com.example.booking.infrastructure.security;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.booking.domain.models.Role;
import com.example.booking.interfaces.Login.JwtService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtServiceImplementation implements JwtService {

    private final Key key = Keys.hmacShaKeyFor(
        Base64.getDecoder().decode("your-256-bit-secret-your-256-bit")
    );

    private final long accessTokenTtl = 15 * 60 * 1000; // 15 min
    private final long refreshTokenTtl = 7 * 24 * 60 * 60 * 1000; // 7 days

    
    @Override
    public String generateAccessToken(UUID userId, Role role){
        return Jwts.builder()
            .setSubject(userId.toString())
            .claim("role", role.name())
            .setExpiration(new Date(System.currentTimeMillis() + accessTokenTtl))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    @Override
    public String generateRefreshToken(UUID userId, Role role){

        return Jwts.builder()
            .setSubject(userId.toString())
            .claim("role", role.name())
            .setExpiration(new Date(System.currentTimeMillis() + refreshTokenTtl))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    @Override
    public boolean validateToken(String token){

        Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token);
        
            return true;
    }

    @Override
    public UUID extractUserId(String token){

        String userId = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();

        return UUID.fromString(userId);
    }

    @Override
    public Role extractUserRole(String token){

        String role = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .get("role", String.class);
        
        return Role.valueOf(role);
    }
     
    
}
