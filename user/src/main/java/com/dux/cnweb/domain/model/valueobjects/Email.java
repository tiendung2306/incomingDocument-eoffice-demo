package com.dux.cnweb.domain.model.valueobjects;

import lombok.Value;
import java.util.regex.Pattern;


@Value
public class Email {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );
    
    String email;

    private Email(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email không được để trống");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Định dạng email không hợp lệ: " + email);
        }
        this.email = email.trim().toLowerCase();
    }

    public static Email of(String email) {
        return new Email(email);
    }
    
    public static Email createValid(String email) {
        return new Email(email);
    }
}
