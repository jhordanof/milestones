package com.project.redpontis.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.redpontis.dto.TaskDTO;
import com.project.redpontis.entity.Task;
import com.project.redpontis.entity.User;
import com.project.redpontis.repository.TaskRepository;
import com.project.redpontis.repository.UserRepository;
import com.project.redpontis.service.TaskService;

import jakarta.transaction.Transactional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Task createTask(TaskDTO dto, Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) throw new IllegalArgumentException("User not found");

        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCompleted(dto.isCompleted());
        task.setUser(userOpt.get());

        return taskRepository.save(task);
    }

    @Override
    public List<Task> findByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Task> update(Long id, Task updatedTask) {
        return taskRepository.update(id, updatedTask);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return taskRepository.delete(id);
    }
}