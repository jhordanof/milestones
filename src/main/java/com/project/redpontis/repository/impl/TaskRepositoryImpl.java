package com.project.redpontis.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.project.redpontis.entity.Task;
import com.project.redpontis.repository.TaskRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Task save(Task task) {
        entityManager.persist(task);
        return task;
    }

    @Override
    public List<Task> findByUserId(Long userId) {
        TypedQuery<Task> query = entityManager.createQuery(
                "SELECT t FROM Task t WHERE t.user.id = :userId", Task.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Task.class, id));
    }

    @Override
    public Optional<Task> update(Long id, Task updatedTask) {
        Task existing = entityManager.find(Task.class, id);
        if (existing == null) return Optional.empty();

        existing.setTitle(updatedTask.getTitle());
        existing.setDescription(updatedTask.getDescription());
        existing.setCompleted(updatedTask.isCompleted());

        return Optional.of(existing);
    }

    @Override
    public boolean delete(Long id) {
        Task task = entityManager.find(Task.class, id);
        if (task != null) {
            entityManager.remove(task);
            return true;
        }
        return false;
    }
}