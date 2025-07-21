package com.project.redpontis.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private Long userId;
}
