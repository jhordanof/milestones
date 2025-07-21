package com.project.redpontis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.redpontis.dto.UserDTO;
import com.project.redpontis.entity.Role;
import com.project.redpontis.entity.User;
import com.project.redpontis.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
	
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User register(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(Role.valueOf(dto.getRole().toUpperCase()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<User> updateUser(Long id, User updatedUser) {
        return userRepository.update(id, updatedUser);
    }

    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        return userRepository.delete(id);
    }
    
    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
