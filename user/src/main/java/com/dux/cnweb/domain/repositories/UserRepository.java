package com.dux.cnweb.domain.repositories;

import com.dux.cnweb.domain.model.aggregates.User;
import com.dux.cnweb.domain.model.valueobjects.Email;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(String userId);
    Optional<User> findByEmail(Email email);
    List<User> findAll();
    void deleteById(String userId);
}
