package com.project.redpontis.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.project.redpontis.entity.User;
import com.project.redpontis.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            User user = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return Optional.of(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public Optional<User> update(Long id, User updatedUser) {
        User existing = entityManager.find(User.class, id);
        if (existing == null) return Optional.empty();

        existing.setUsername(updatedUser.getUsername());
        existing.setPassword(updatedUser.getPassword());
        existing.setRole(updatedUser.getRole());

        return Optional.of(existing);
    }

    @Override
    public boolean delete(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
            return true;
        }
        return false;
    }
}