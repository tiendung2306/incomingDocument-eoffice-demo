package com.dux.cnweb.domain.model.aggregates;

import com.dux.cnweb.domain.events.UserCreatedEvent;
import com.dux.cnweb.domain.events.UserLoginEvent;
import com.dux.cnweb.shared.domain.model.AggregateRoot;
import com.dux.cnweb.domain.model.entities.UserId;
import com.dux.cnweb.domain.model.valueobjects.Email;
import com.dux.cnweb.domain.model.valueobjects.PhoneNumber;
import com.dux.cnweb.domain.model.valueobjects.JobTitle;
import com.dux.cnweb.domain.model.valueobjects.Password;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class User extends AggregateRoot {

    private UserId userId;
    private Email email;
    private PhoneNumber phoneNumber;
    private JobTitle jobTitle;
    private Password password;
    private LocalDateTime createdAt;
    private LocalDateTime lastLoginAt;

    // Factory method để tạo User mới
    public static User create(UserId userId, Email email, PhoneNumber phoneNumber, JobTitle jobTitle, Password encodedPassword) {
        User user = new User();
        user.userId = userId;
        user.email = email;
        user.phoneNumber = phoneNumber;
        user.jobTitle = jobTitle;
        user.password = encodedPassword;
        user.createdAt = LocalDateTime.now();
        
        // Phát sinh domain event
        user.addDomainEvent(new UserCreatedEvent(userId.getValue(), email.getEmail()));
        
        return user;
    }

    // Business method để xác thực đăng nhập
    public boolean authenticate(String rawPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(rawPassword, this.password.getValue());
    }

    // Business method để cập nhật thời gian đăng nhập cuối
    public void recordLogin() {
        this.lastLoginAt = LocalDateTime.now();
        addDomainEvent(new UserLoginEvent(this.userId.getValue(), this.email.getEmail()));
    }

    // Business method để update user
    public void updateEmail(Email email) {
        this.email = email;
        
        // Phát sinh domain event
        // addDomainEvent(new UserEmailUpdatedEvent(this.userId.getValue(), email));
    }

    // Business method để update phone number
    public void updatePhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        
        // Phát sinh domain event
        // addDomainEvent(new UserPhoneUpdatedEvent(this.userId.getValue(), phoneNumber));
    }

    // Business method để update job title
    public void updateJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
        
        // Phát sinh domain event
        // addDomainEvent(new UserJobTitleUpdatedEvent(this.userId.getValue(), jobTitle));
    }

    // Business method để change password
    public void changePassword(Password password) {
        this.password = password;
        
        // Phát sinh domain event
        // addDomainEvent(new UserPasswordChangedEvent(this.userId.getValue()));
    }
    
    // Constructor cho việc khôi phục từ database
    public User(UserId userId, Email email, PhoneNumber phoneNumber, JobTitle jobTitle, Password password, LocalDateTime createdAt, LocalDateTime lastLoginAt) {
        this.userId = userId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.jobTitle = jobTitle;
        this.password = password;
        this.createdAt = createdAt;
        this.lastLoginAt = lastLoginAt;
    }

    // Interface cho password encoder
    public interface PasswordEncoder {
        boolean matches(String rawPassword, String encodedPassword);
    }
}
