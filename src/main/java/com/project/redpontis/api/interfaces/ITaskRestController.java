package com.project.redpontis.api.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.redpontis.dto.TaskDTO;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Validated
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Task", description = "Services Task")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exitoso", content = @Content(schema = @Schema(implementation = TaskDTO.class))),
        @ApiResponse(responseCode = "401", description = "Error", content = @Content)
})
public interface ITaskRestController {

    @PostMapping("/tasks")
    ResponseEntity<TaskDTO> create(@RequestBody TaskDTO dto);

    @GetMapping("/tasks")
    ResponseEntity<List<TaskDTO>> getAllByUser(@RequestParam Long userId);

    @GetMapping("/tasks/{id}")
    ResponseEntity<TaskDTO> getById(@PathVariable Long id);

    @PutMapping("/tasks/{id}")
    ResponseEntity<TaskDTO> update(@PathVariable Long id, @RequestBody TaskDTO dto);

    @DeleteMapping("/tasks/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}