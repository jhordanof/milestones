package com.project.redpontis.mapper;

import com.project.redpontis.dto.TaskDTO;
import com.project.redpontis.dto.UserDTO;
import com.project.redpontis.entity.Task;
import com.project.redpontis.entity.User;

public interface DtoMapper {
    UserDTO toUserDTO(User user);

    TaskDTO toTaskDTO(Task task);
}
