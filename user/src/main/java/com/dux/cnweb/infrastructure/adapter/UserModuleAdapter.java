package com.dux.cnweb.infrastructure.adapter;

import com.dux.cnweb.application.ports.UserControllers;
import com.dux.cnweb.application.dto.UserDto;
import com.dux.cnweb.application.handlers.AuthenticationCommandHandler;
import com.dux.cnweb.application.commands.RegisterUserCommand;
import com.dux.cnweb.domain.model.aggregates.User;
import com.dux.cnweb.domain.repositories.UserRepository;
import com.dux.cnweb.infrastructure.adapter.dto.CreateUserRequest;
import com.dux.cnweb.infrastructure.security.SimplePasswordEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserModuleAdapter implements UserControllers {
    
    private final AuthenticationCommandHandler authHandler;
    private final UserRepository userRepository;
    private final SimplePasswordEncoder passwordEncoder;

    @Override
    public String createUser(CreateUserRequest request) {
        try {
            log.debug("Creating user with email: {}", request.getEmail());
            
            // Sử dụng authentication handler để tạo user
            RegisterUserCommand command = RegisterUserCommand.of(
                request.getEmail(),
                request.getPassword(),
                request.getPhoneNumber(),
                request.getJobTitle()
            );
            
            var authResponse = authHandler.handle(command);
            
            log.info("User created successfully with ID: {}", authResponse.getUserId());
            return authResponse.getUserId();
            
        } catch (Exception e) {
            log.error("Error creating user with email: {}", request.getEmail(), e);
            throw new RuntimeException("Failed to create user: " + e.getMessage(), e);
        }
    }

    @Override
    public UserDto getUser(String userId) {
        try {
            log.debug("Getting user with ID: {}", userId);
            
            User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
            
            return mapToDto(user);
            
        } catch (Exception e) {
            log.error("Error getting user with ID: {}", userId, e);
            throw new RuntimeException("Failed to get user: " + e.getMessage(), e);
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        try {
            log.debug("Getting all users");
            
            List<User> users = userRepository.findAll();
            
            return users.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
                
        } catch (Exception e) {
            log.error("Error getting all users", e);
            throw new RuntimeException("Failed to get users: " + e.getMessage(), e);
        }
    }

    /**
     * Phương thức để các module khác có thể tìm user theo email
     * @param email Email của user
     * @return UserDto hoặc null nếu không tìm thấy
     */
    @Override
    public UserDto getUserByEmail(String email) {
        try {
            log.debug("Getting user with email: {}", email);
            
            return userRepository.findByEmail(com.dux.cnweb.domain.model.valueobjects.Email.of(email))
                .map(this::mapToDto)
                .orElse(null);
                
        } catch (Exception e) {
            log.error("Error getting user with email: {}", email, e);
            return null;
        }
    }

    /**
     * Phương thức để verify user credentials (cho other modules)
     * @param email Email
     * @param password Raw password
     * @return true nếu credentials hợp lệ
     */
    @Override
    public boolean verifyUserCredentials(String email, String password) {
        try {
            log.debug("Verifying credentials for email: {}", email);
            
            User user = userRepository.findByEmail(com.dux.cnweb.domain.model.valueobjects.Email.of(email))
                .orElse(null);
                
            if (user == null) {
                return false;
            }
            
            // Sử dụng password encoder để verify
            return user.authenticate(password, passwordEncoder);
                
        } catch (Exception e) {
            log.error("Error verifying credentials for email: {}", email, e);
            return false;
        }
    }

    private UserDto mapToDto(User user) {
        return new UserDto(
            user.getUserId().getValue(),
            user.getEmail().getEmail(),
            user.getPhoneNumber().getNumber(),
            user.getJobTitle().getDisplayName()
        );
    }
}
