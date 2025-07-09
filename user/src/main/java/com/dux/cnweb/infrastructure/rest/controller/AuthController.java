package com.dux.cnweb.infrastructure.rest.controller;

import com.dux.cnweb.application.commands.LoginUserCommand;
import com.dux.cnweb.application.commands.RegisterUserCommand;
import com.dux.cnweb.application.dto.AuthenticationResponse;
import com.dux.cnweb.application.handlers.AuthenticationCommandHandler;
import com.dux.cnweb.application.ports.AuthenticationController;
import com.dux.cnweb.infrastructure.rest.dto.LoginRequest;
import com.dux.cnweb.infrastructure.rest.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController implements AuthenticationController {

    private final AuthenticationCommandHandler commandHandler;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerRest(@Valid @RequestBody RegisterRequest request) {
        AuthenticationResponse response = register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginRest(@Valid @RequestBody LoginRequest request) {
        AuthenticationResponse response = login(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        try {
            RegisterUserCommand command = RegisterUserCommand.of(
                    request.getEmail(),
                    request.getPassword(),
                    request.getPhoneNumber(),
                    request.getJobTitle()
            );
            
            return commandHandler.handle(command);
        } catch (Exception e) {
            throw new RuntimeException("Registration failed: " + e.getMessage(), e);
        }
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) {
        try {
            LoginUserCommand command = LoginUserCommand.of(
                    request.getEmail(),
                    request.getPassword()
            );
            
            return commandHandler.handle(command);
        } catch (Exception e) {
            throw new RuntimeException("Login failed: " + e.getMessage(), e);
        }
    }
}
