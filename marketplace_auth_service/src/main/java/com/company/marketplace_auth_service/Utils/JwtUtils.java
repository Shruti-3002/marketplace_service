package com.company.marketplace_auth_service.Utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtUtils {

    Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    
    private static final String SECRET_KEY = "TmV3U2VjcmV0S2V5Rm9ySldUU2lnbmluZ1B1cnBvc2VzMTIzNDU2Nzg=";

    // Generate an Access Token with shorter expiration
    public String generateAccessToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return generateToken(email, claims, 15 * 60 * 1000); // 15 minutes expiration
    }

    // Generate a Refresh Token with longer expiration
    public String generateRefreshToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return generateToken(email, claims, 7 * 24 * 60 * 60 * 1000); // 7 days expiration
    }

    private String generateToken(String subject, Map<String, Object> claims, long expiration) {
        return Jwts.builder()
                .setClaims(claims) // Add payload
                .setSubject(subject) // Add subject (e.g., email)
                .setIssuedAt(new Date(System.currentTimeMillis())) // Set issue time
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // Set expiration
                .signWith(getKey(), SignatureAlgorithm.HS256) // Sign with the secret key
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessTokenFromRefreshToken(String refreshToken) {
        try {
            logger.info("Refresh Token: {}", refreshToken);
            // Parse and validate the refresh token
            String email = Jwts.parserBuilder()
                    .setSigningKey(getKey()) // Use the same secret key
                    .build()
                    .parseClaimsJws(refreshToken) // Validate the token
                    .getBody()
                    .getSubject(); // Extract the subject (e.g., email)

            logger.info("Email: {}", email);
            // If the refresh token is valid, generate a new access token
            return generateAccessToken(email);
        } catch (Exception e) {
            logger.error("Error while generating access token from refresh token: {}", e.getMessage());
            throw new RuntimeException("Invalid refresh token", e); // Handle token validation errors
        }
    }

}
