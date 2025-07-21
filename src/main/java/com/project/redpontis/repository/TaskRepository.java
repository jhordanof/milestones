package com.project.redpontis.repository;

import java.util.List;
import java.util.Optional;

import com.project.redpontis.entity.Task;


public interface TaskRepository{

    Task save(Task task);
    List<Task> findByUserId(Long userId);
    Optional<Task> findById(Long id);
    Optional<Task> update(Long id, Task updatedTask);
    boolean delete(Long id);
    
}