package com.project.redpontis.service;

import java.util.List;
import java.util.Optional;

import com.project.redpontis.dto.UserDTO;
import com.project.redpontis.entity.User;

public interface UserService {
	
    User register(UserDTO dto);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    Optional<User> updateUser(Long id, User updatedUser);
    boolean deleteUser(Long id);
    Optional<User> getByUsername(String username);
}
