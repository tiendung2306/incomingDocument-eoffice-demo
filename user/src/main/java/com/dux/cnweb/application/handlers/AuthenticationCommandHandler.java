package com.dux.cnweb.application.handlers;

import com.dux.cnweb.application.commands.LoginUserCommand;
import com.dux.cnweb.application.commands.RegisterUserCommand;
import com.dux.cnweb.application.dto.AuthenticationResponse;
import com.dux.cnweb.domain.model.aggregates.User;
import com.dux.cnweb.domain.model.entities.UserId;
import com.dux.cnweb.domain.model.valueobjects.Password;
import com.dux.cnweb.domain.repositories.UserRepository;
import com.dux.cnweb.shared.infrastructure.events.DomainEventPublisher;
import com.dux.cnweb.infrastructure.security.JwtTokenProvider;
import com.dux.cnweb.infrastructure.security.SimplePasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationCommandHandler {
    
    private final UserRepository userRepository;
    private final DomainEventPublisher eventPublisher;
    private final JwtTokenProvider jwtTokenProvider;
    private final SimplePasswordEncoder passwordEncoder;

    public AuthenticationResponse handle(RegisterUserCommand command) {
        // Kiểm tra email đã tồn tại chưa
        if (userRepository.findByEmail(command.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã được sử dụng: " + command.getEmail().getEmail());
        }

        // Tạo user mới
        UserId userId = UserId.newId();
        Password encodedPassword = Password.of(passwordEncoder.encode(command.getPassword().getValue()));
        
        User user = User.create(userId, command.getEmail(), command.getPhoneNumber(), command.getJobTitle(), encodedPassword);
        
        // Lưu user
        userRepository.save(user);
        
        // Publish domain events
        eventPublisher.publishEvents(user.getDomainEvents());
        user.clearDomainEvents();
        
        // Tạo access token
        String accessToken = jwtTokenProvider.createToken(userId.getValue(), command.getEmail().getEmail());
        
        return AuthenticationResponse.of(accessToken, userId.getValue(), command.getEmail().getEmail());
    }

    public AuthenticationResponse handle(LoginUserCommand command) {
        // Tìm user theo email
        User user = userRepository.findByEmail(command.getEmail())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user với email: " + command.getEmail().getEmail()));
        
        // Xác thực password
        if (!user.authenticate(command.getPassword(), passwordEncoder)) {
            throw new RuntimeException("Mật khẩu không chính xác");
        }
        
        // Cập nhật last login
        user.recordLogin();
        userRepository.save(user);
        
        // Publish domain events
        eventPublisher.publishEvents(user.getDomainEvents());
        user.clearDomainEvents();
        
        // Tạo access token
        String accessToken = jwtTokenProvider.createToken(user.getUserId().getValue(), user.getEmail().getEmail());
        
        return AuthenticationResponse.of(accessToken, user.getUserId().getValue(), user.getEmail().getEmail());
    }
}
