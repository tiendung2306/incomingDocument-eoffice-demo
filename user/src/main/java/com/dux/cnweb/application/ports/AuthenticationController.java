package com.dux.cnweb.application.ports;

import com.dux.cnweb.application.dto.AuthenticationResponse;
import com.dux.cnweb.infrastructure.rest.dto.LoginRequest;
import com.dux.cnweb.infrastructure.rest.dto.RegisterRequest;

public interface AuthenticationController {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse login(LoginRequest request);
}
