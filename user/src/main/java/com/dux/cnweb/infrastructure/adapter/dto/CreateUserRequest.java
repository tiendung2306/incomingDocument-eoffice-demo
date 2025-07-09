package com.dux.cnweb.infrastructure.adapter.dto;

import lombok.Data;

@Data
public class CreateUserRequest {
    
    private String email;
    private String phoneNumber;
    private String jobTitle;
    private String password;
}
