package com.dux.cnweb.application.commands;

import com.dux.cnweb.domain.model.valueobjects.Email;
import lombok.Value;

@Value
public class LoginUserCommand {
    Email email;
    String password;
    
    public static LoginUserCommand of(String email, String password) {
        return new LoginUserCommand(Email.of(email), password);
    }
}
