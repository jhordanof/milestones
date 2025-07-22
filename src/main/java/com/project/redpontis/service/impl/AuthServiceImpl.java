package com.project.redpontis.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.redpontis.entity.User;
import com.project.redpontis.repository.AuthRepository;
import com.project.redpontis.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public Optional<User> login(String username, String password) {
        return authRepository.login(username, password);
    }
}