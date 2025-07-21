package com.project.redpontis.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.project.redpontis.api.interfaces.ITaskRestController;
import com.project.redpontis.dto.TaskDTO;
import com.project.redpontis.entity.Task;
import com.project.redpontis.mapper.DtoMapper;
import com.project.redpontis.service.TaskService;

@RestController
public class TaskRestController implements ITaskRestController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private DtoMapper dtoMapper;

    @Override
    public ResponseEntity<TaskDTO> create(TaskDTO dto) {
        Task task = taskService.createTask(dto, dto.getUserId());
        return ResponseEntity.ok(dtoMapper.toTaskDTO(task));
    }

    @Override
    public ResponseEntity<List<TaskDTO>> getAllByUser(Long userId) {
        return ResponseEntity.ok(taskService.findByUserId(userId)
                .stream().map(dtoMapper::toTaskDTO).toList());
    }

    @Override
    public ResponseEntity<TaskDTO> getById(Long id) {
        return taskService.findById(id)
                .map(dtoMapper::toTaskDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<TaskDTO> update(Long id, TaskDTO dto) {
        Task updated = new Task();
        updated.setTitle(dto.getTitle());
        updated.setDescription(dto.getDescription());
        updated.setCompleted(dto.isCompleted());

        return taskService.update(id, updated)
                .map(dtoMapper::toTaskDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return taskService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}