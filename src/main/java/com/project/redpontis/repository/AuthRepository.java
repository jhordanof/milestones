package com.project.redpontis.repository;

import java.util.Optional;

import com.project.redpontis.entity.User;

public interface AuthRepository {
    Optional<User> login(String username, String password);
}
