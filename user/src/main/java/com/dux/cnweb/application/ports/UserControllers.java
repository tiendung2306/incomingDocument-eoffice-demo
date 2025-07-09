package com.dux.cnweb.application.ports;

import com.dux.cnweb.application.dto.UserDto;
import com.dux.cnweb.infrastructure.adapter.dto.CreateUserRequest;
import java.util.List;

/**
 * Port interface để các module khác có thể gọi sang user module
 * Đây là public API của user module cho inter-module communication
 */
public interface UserControllers {
    
    /**
     * Tạo user mới
     * @param request Thông tin user cần tạo
     * @return User ID của user được tạo
     */
    String createUser(CreateUserRequest request);
    
    /**
     * Lấy thông tin user theo ID
     * @param userId ID của user
     * @return UserDto hoặc throw exception nếu không tìm thấy
     */
    UserDto getUser(String userId);
    
    /**
     * Lấy danh sách tất cả users
     * @return Danh sách UserDto
     */
    List<UserDto> getAllUsers();
    
    /**
     * Tìm user theo email (cho inter-module communication)
     * @param email Email của user
     * @return UserDto hoặc null nếu không tìm thấy
     */
    UserDto getUserByEmail(String email);
    
    /**
     * Verify user credentials (cho authentication từ module khác)
     * @param email Email
     * @param password Raw password
     * @return true nếu credentials hợp lệ
     */
    boolean verifyUserCredentials(String email, String password);
}