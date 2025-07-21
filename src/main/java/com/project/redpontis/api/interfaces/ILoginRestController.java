package com.project.redpontis.api.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.redpontis.dto.LoginDTO;
import com.project.redpontis.dto.UserDTO;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Validated
@Tag(name = "Login", description = "Servicios de autenticación")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login exitoso", content = @Content(schema = @Schema(implementation = LoginDTO.class))),
        @ApiResponse(responseCode = "401", description = "Credenciales invalidas", content = @Content)
})
public interface ILoginRestController {

    @PostMapping("/auth/login")
    ResponseEntity<?> login(@RequestBody LoginDTO loginDTO);
    
    @PostMapping("/auth/register")
    ResponseEntity<UserDTO> register(@RequestBody UserDTO dto);
}
