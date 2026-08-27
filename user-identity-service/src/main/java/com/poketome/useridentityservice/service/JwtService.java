package com.poketome.useridentityservice.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    // A secure 256-bit secret key used to sign tokens.
    // In production, this would be injected securely via environment variables.
    private final String SECRET_STRING = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private final SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_STRING.getBytes());

    // Tokens expire after 24 hours (in milliseconds)
    private final long EXPIRATION_TIME = 86400000;

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username) // Store the username inside the token payload
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey) // Cryptographically sign it with our secret key
                .compact(); // Build and serialize into a compact string
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}