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

import com.project.redpontis.dto.UserDTO;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Validated
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "User", description = "Servicios para gestión de usuarios")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exitoso", content = @Content(schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(responseCode = "401", description = "Error", content = @Content)
})
public interface IUserRestController {

    @PostMapping("/register")
    ResponseEntity<UserDTO> register(@RequestBody UserDTO dto);

    @GetMapping("/users")
    ResponseEntity<List<UserDTO>> getAllUsers();

    @GetMapping("/users/{id}")
    ResponseEntity<UserDTO> getUserById(@PathVariable Long id);

    @PutMapping("/users/{id}")
    ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO dto);

    @DeleteMapping("/users/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable Long id);
}