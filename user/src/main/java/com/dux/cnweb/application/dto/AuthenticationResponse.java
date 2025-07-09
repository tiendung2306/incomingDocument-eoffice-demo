package com.dux.cnweb.application.dto;

import lombok.Value;

@Value
public class AuthenticationResponse {
    String accessToken;
    String tokenType;
    String userId;
    String email;
    
    public static AuthenticationResponse of(String accessToken, String userId, String email) {
        return new AuthenticationResponse(accessToken, "Bearer", userId, email);
    }
}
