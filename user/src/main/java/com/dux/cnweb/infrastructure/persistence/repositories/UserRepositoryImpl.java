package com.dux.cnweb.infrastructure.persistence.repositories;

import com.dux.cnweb.domain.model.aggregates.User;
import com.dux.cnweb.domain.model.entities.UserId;
import com.dux.cnweb.domain.model.valueobjects.Email;
import com.dux.cnweb.domain.model.valueobjects.JobTitle;
import com.dux.cnweb.domain.model.valueobjects.Password;
import com.dux.cnweb.domain.model.valueobjects.PhoneNumber;
import com.dux.cnweb.domain.repositories.UserRepository;
import com.dux.cnweb.infrastructure.persistence.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaRepository;

    @Override
    public User save(User user) {
        UserEntity entity = mapToEntity(user);
        UserEntity saved = jpaRepository.save(entity);
        return mapToDomain(saved);
    }

    @Override
    public Optional<User> findById(String userId) {
        return jpaRepository.findById(userId)
                .map(this::mapToDomain);
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return jpaRepository.findByEmail(email.getEmail())
                .map(this::mapToDomain);
    }

    @Override
    public List<User> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String userId) {
        jpaRepository.deleteById(userId);
    }

    private UserEntity mapToEntity(User user) {
        return new UserEntity(
                user.getUserId().getValue(),
                user.getEmail().getEmail(),
                user.getPhoneNumber().getNumber(),
                user.getJobTitle().getDisplayName(),
                user.getPassword().getValue(),
                user.getCreatedAt(),
                user.getLastLoginAt()
        );
    }

    private User mapToDomain(UserEntity entity) {
        return new User(
                UserId.of(entity.getUserId()),
                Email.of(entity.getEmail()),
                PhoneNumber.of(entity.getPhoneNumber()),
                JobTitle.of(entity.getJobTitle()),
                Password.of(entity.getPassword()),
                entity.getCreatedAt(),
                entity.getLastLoginAt()
        );
    }
}
