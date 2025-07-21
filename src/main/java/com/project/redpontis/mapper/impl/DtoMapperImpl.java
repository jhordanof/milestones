package com.project.redpontis.mapper.impl;

import org.springframework.stereotype.Component;

import com.project.redpontis.dto.TaskDTO;
import com.project.redpontis.dto.UserDTO;
import com.project.redpontis.entity.Task;
import com.project.redpontis.entity.User;
import com.project.redpontis.mapper.DtoMapper;

@Component
public class DtoMapperImpl implements DtoMapper {

    @Override
    public UserDTO toUserDTO(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole().name());
        return dto;
    }

    @Override
    public TaskDTO toTaskDTO(Task task) {
        if (task == null) return null;

        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCompleted(task.isCompleted());
        return dto;
    }
}