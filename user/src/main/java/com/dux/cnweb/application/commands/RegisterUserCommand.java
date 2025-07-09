package com.dux.cnweb.application.commands;

import com.dux.cnweb.domain.model.valueobjects.Email;
import com.dux.cnweb.domain.model.valueobjects.Password;
import com.dux.cnweb.domain.model.valueobjects.PhoneNumber;
import com.dux.cnweb.domain.model.valueobjects.JobTitle;
import lombok.Value;

@Value
public class RegisterUserCommand {
    Email email;
    Password password;
    PhoneNumber phoneNumber;
    JobTitle jobTitle;

    public static RegisterUserCommand of(String email, String password, String phoneNumber, String jobTitle) {
        return new RegisterUserCommand(
            Email.of(email),
            Password.of(password),
            PhoneNumber.of(phoneNumber),
            JobTitle.of(jobTitle)
        );
    }
}
