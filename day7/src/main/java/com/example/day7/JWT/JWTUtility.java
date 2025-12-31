package com.example.day7.JWT;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JWTUtility {

    private final String SECRET = "super_secret_key_1234567890";
    private final long EXPIRATION = 15 * 60 * 1000; // 15 minutes

    public String generateToken(String username) {

         return Jwts.builder()
            .subject(username)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
            .signWith(getSignInKey())
            .compact();
    }

    private SecretKey getSignInKey() {
        
        byte[] bytes = Base64.getDecoder()
            .decode(SECRET.getBytes(StandardCharsets.UTF_8));
        
        return new SecretKeySpec(bytes, "HmacSHA256"); 
    }


    private Claims extractAllClaims(String token){
        return Jwts.parser()
            .verifyWith(getSignInKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }


}

