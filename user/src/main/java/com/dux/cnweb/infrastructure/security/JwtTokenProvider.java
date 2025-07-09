package com.dux.cnweb.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenProvider {
    
    @Value("${app.jwt.secret:dGhpc0lzQVNlY3JldEtleUZvckpXVEF1dGhlbnRpY2F0aW9uU2VjcmV0S2V5}")
    private String jwtSecret;
    
    @Value("${app.jwt.expiration:86400000}") // 24 hours
    private int jwtExpirationInMs;
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    public String createToken(String userId, String email) {
        try {
            // Create header
            Map<String, Object> header = new HashMap<>();
            header.put("alg", "HS256");
            header.put("typ", "JWT");
            
            // Create payload
            Map<String, Object> payload = new HashMap<>();
            payload.put("sub", userId);
            payload.put("email", email);
            payload.put("iat", Instant.now().getEpochSecond());
            payload.put("exp", Instant.now().plus(jwtExpirationInMs, ChronoUnit.MILLIS).getEpochSecond());
            
            // Encode header and payload
            String encodedHeader = Base64.getUrlEncoder().withoutPadding()
                .encodeToString(objectMapper.writeValueAsString(header).getBytes(StandardCharsets.UTF_8));
            String encodedPayload = Base64.getUrlEncoder().withoutPadding()
                .encodeToString(objectMapper.writeValueAsString(payload).getBytes(StandardCharsets.UTF_8));
            
            // Create signature
            String data = encodedHeader + "." + encodedPayload;
            String signature = createSignature(data);
            
            return data + "." + signature;
        } catch (Exception e) {
            throw new RuntimeException("Error creating JWT token", e);
        }
    }
    
    public String getUserIdFromToken(String token) {
        try {
            Map<String, Object> payload = extractPayload(token);
            return (String) payload.get("sub");
        } catch (Exception e) {
            throw new RuntimeException("Error extracting user ID from token", e);
        }
    }
    
    public String getEmailFromToken(String token) {
        try {
            Map<String, Object> payload = extractPayload(token);
            return (String) payload.get("email");
        } catch (Exception e) {
            throw new RuntimeException("Error extracting email from token", e);
        }
    }
    
    public boolean validateToken(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                return false;
            }
            
            String data = parts[0] + "." + parts[1];
            String signature = parts[2];
            
            // Verify signature
            if (!createSignature(data).equals(signature)) {
                return false;
            }
            
            // Check expiration
            Map<String, Object> payload = extractPayload(token);
            Long exp = ((Number) payload.get("exp")).longValue();
            return Instant.now().getEpochSecond() < exp;
            
        } catch (Exception e) {
            log.error("Token validation error: {}", e.getMessage());
            return false;
        }
    }
    
    private Map<String, Object> extractPayload(String token) throws Exception {
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid JWT format");
        }
        
        String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
        @SuppressWarnings("unchecked")
        Map<String, Object> payload = objectMapper.readValue(payloadJson, Map.class);
        return payload;
    }
    
    private String createSignature(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String signData = data + jwtSecret;
            byte[] hash = digest.digest(signData.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error creating signature", e);
        }
    }
}
