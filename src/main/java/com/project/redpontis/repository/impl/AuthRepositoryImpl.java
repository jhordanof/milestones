package com.project.redpontis.repository.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.project.redpontis.entity.User;
import com.project.redpontis.repository.AuthRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Repository
public class AuthRepositoryImpl implements AuthRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> login(String username, String password) {
        try {
            User user = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
            return Optional.of(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}