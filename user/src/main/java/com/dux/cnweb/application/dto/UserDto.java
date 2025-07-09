package com.dux.cnweb.application.dto;

import lombok.Value;

@Value
public class UserDto {
    String userId;
    String email;
    String phoneNumber;
    String jobTitle;
}
