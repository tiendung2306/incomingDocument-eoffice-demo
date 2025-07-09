package com.dux.cnweb.application.ports;

import com.dux.cnweb.application.dto.UserDto;
import com.dux.cnweb.infrastructure.adapter.dto.CreateUserRequest;
import java.util.List;

public interface UserControllers {
    String createUser(CreateUserRequest request);
    UserDto getUser(String userId);
    List<UserDto> getAllUsers();
}
