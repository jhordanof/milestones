package com.project.redpontis.service;

import java.util.List;
import java.util.Optional;

import com.project.redpontis.dto.TaskDTO;
import com.project.redpontis.entity.Task;

public interface TaskService {
	
    Task createTask(TaskDTO dto, Long userId);
    List<Task> findByUserId(Long userId);
    Optional<Task> findById(Long id);
    Optional<Task> update(Long id, Task updatedTask);
    boolean delete(Long id);
    
}