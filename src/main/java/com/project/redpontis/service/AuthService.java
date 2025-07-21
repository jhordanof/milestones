package com.project.redpontis.service;

import java.util.Optional;

import com.project.redpontis.entity.User;

public interface AuthService {
    Optional<User> login(String username, String password);
}