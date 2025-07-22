package com.project.redpontis.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.redpontis.dto.TaskDTO;
import com.project.redpontis.entity.Task;
import com.project.redpontis.entity.User;
import com.project.redpontis.repository.TaskRepository;
import com.project.redpontis.repository.UserRepository;

class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTask_shouldSaveTaskWhenUserExists() {
        Long userId = 1L;

        TaskDTO dto = new TaskDTO();
        dto.setTitle("Nueva Tarea");
        dto.setDescription("Es una nueva tarea");
        dto.setCompleted(false);

        User user = new User();
        user.setId(userId);

        Task savedTask = new Task();
        savedTask.setTitle(dto.getTitle());
        savedTask.setDescription(dto.getDescription());
        savedTask.setCompleted(dto.isCompleted());
        savedTask.setUser(user);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        Task result = taskService.createTask(dto, userId);

        assertNotNull(result);
        assertEquals("Nueva Tarea", result.getTitle());
        assertEquals("Es una nueva tarea", result.getDescription());
        assertFalse(result.isCompleted());
        assertEquals(user, result.getUser());

        verify(userRepository).findById(userId);
        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void createTask_shouldThrowExceptionWhenUserNotFound() {
        Long userId = 99L;
        TaskDTO dto = new TaskDTO();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> taskService.createTask(dto, userId));

        verify(userRepository).findById(userId);
        verifyNoInteractions(taskRepository);
    }

    @Test
    void findByUserId_shouldReturnTaskList() {
        Long userId = 1L;
        List<Task> tasks = Arrays.asList(new Task(), new Task());

        when(taskRepository.findByUserId(userId)).thenReturn(tasks);

        List<Task> result = taskService.findByUserId(userId);

        assertEquals(2, result.size());
        verify(taskRepository).findByUserId(userId);
    }

    @Test
    void findById_shouldReturnTask() {
        Long taskId = 1L;
        Task task = new Task();
        task.setId(taskId);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        Optional<Task> result = taskService.findById(taskId);

        assertTrue(result.isPresent());
        assertEquals(taskId, result.get().getId());
        verify(taskRepository).findById(taskId);
    }

    @Test
    void update_shouldReturnUpdatedTask() {
        Long taskId = 1L;
        Task updatedTask = new Task();
        updatedTask.setTitle("Updated");

        when(taskRepository.update(taskId, updatedTask)).thenReturn(Optional.of(updatedTask));

        Optional<Task> result = taskService.update(taskId, updatedTask);

        assertTrue(result.isPresent());
        assertEquals("Updated", result.get().getTitle());
        verify(taskRepository).update(taskId, updatedTask);
    }

    @Test
    void delete_shouldReturnTrueIfDeleted() {
        Long taskId = 1L;

        when(taskRepository.delete(taskId)).thenReturn(true);

        boolean result = taskService.delete(taskId);

        assertTrue(result);
        verify(taskRepository).delete(taskId);
    }
}