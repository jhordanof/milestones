package com.project.redpontis.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.redpontis.entity.User;
import com.project.redpontis.repository.AuthRepository;

class AuthServiceImplTest {

    @Mock
    private AuthRepository authRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_ValidCredentials_ReturnsUser() {
        String username = "jhordano";
        String password = "123456";

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        when(authRepository.login(username, password)).thenReturn(Optional.of(user));

        Optional<User> result = authService.login(username, password);

        assertTrue(result.isPresent());
        assertEquals(username, result.get().getUsername());
        assertEquals(password, result.get().getPassword());
        verify(authRepository, times(1)).login(username, password);
    }

    @Test
    void login_InvalidCredentials_ReturnsEmpty() {
        String username = "andrea";
        String password = "andrea";

        when(authRepository.login(username, password)).thenReturn(Optional.empty());

        Optional<User> result = authService.login(username, password);

        assertFalse(result.isPresent());
        verify(authRepository, times(1)).login(username, password);
    }
}