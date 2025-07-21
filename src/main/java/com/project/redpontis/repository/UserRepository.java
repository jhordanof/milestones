package com.project.redpontis.repository;

import java.util.List;
import java.util.Optional;

import com.project.redpontis.entity.User;

public interface UserRepository{
	
    User save(User user);
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
    List<User> findAll();
    Optional<User> update(Long id, User updatedUser);
    boolean delete(Long id);
}