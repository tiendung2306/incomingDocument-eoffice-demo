package com.dux.cnweb.infrastructure.adapter;

import com.dux.cnweb.application.ports.UserControllers;
import com.dux.cnweb.application.dto.UserDto;
import com.dux.cnweb.infrastructure.adapter.dto.CreateUserRequest;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserModuleAdapter implements UserControllers {
    @Override
    public String createUser(CreateUserRequest request) {
        // Implementation for creating a user
        return "User created with email: " + request.getEmail();
    }

    @Override
    public UserDto getUser(String userId) {
        // Implementation for retrieving a user by ID
        return new UserDto(userId, "mail@gmail.com", "1234567890", "Developer");
    }

    @Override
    public List<UserDto> getAllUsers() {
        // Implementation for retrieving all users
        return List.of(
            new UserDto("1", "mail@gmail.com", "1234567890", "Developer"),
            new UserDto("2", "mail2@gmail.com", "0987654321", "Manager")
        );
    }
}
