package com.project.redpontis.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.redpontis.dto.UserDTO;
import com.project.redpontis.entity.Role;
import com.project.redpontis.entity.User;
import com.project.redpontis.repository.UserRepository;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_ShouldSaveAndReturnUser() {
        UserDTO dto = new UserDTO();
        dto.setUsername("jhordano");
        dto.setPassword("123456");
        dto.setRole("USER");

        User savedUser = new User();
        savedUser.setUsername(dto.getUsername());
        savedUser.setPassword(dto.getPassword());
        savedUser.setRole(Role.USER);

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = userService.register(dto);

        assertNotNull(result);
        assertEquals("jhordano", result.getUsername());
        assertEquals("123456", result.getPassword());
        assertEquals(Role.USER, result.getRole());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void getAllUsers_ShouldReturnUserList() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        verify(userRepository).findAll();
    }

    @Test
    void getUserById_ShouldReturnUser() {
        Long id = 1L;
        User user = new User();
        user.setId(id);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        verify(userRepository).findById(id);
    }

    @Test
    void updateUser_ShouldUpdateUser() {
        Long id = 1L;
        User updatedUser = new User();
        updatedUser.setUsername("updated");

        when(userRepository.update(id, updatedUser)).thenReturn(Optional.of(updatedUser));

        Optional<User> result = userService.updateUser(id, updatedUser);

        assertTrue(result.isPresent());
        assertEquals("updated", result.get().getUsername());
        verify(userRepository).update(id, updatedUser);
    }

    @Test
    void deleteUser_ShouldReturnTrueIfDeleted() {
        Long id = 1L;

        when(userRepository.delete(id)).thenReturn(true);

        boolean result = userService.deleteUser(id);

        assertTrue(result);
        verify(userRepository).delete(id);
    }

    @Test
    void getByUsername_ShouldReturnUser() {
        String username = "jhordano";
        User user = new User();
        user.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getByUsername(username);

        assertTrue(result.isPresent());
        assertEquals(username, result.get().getUsername());
        verify(userRepository).findByUsername(username);
    }
}