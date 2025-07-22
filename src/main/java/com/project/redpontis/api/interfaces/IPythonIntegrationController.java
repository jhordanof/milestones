package com.project.redpontis.api.interfaces;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Python Integracion", description = "Consumo del microservicio Python (/ping y /convert)")
@RequestMapping("/python")
public interface IPythonIntegrationController {

    @Operation(summary = "Ping al microservicio", responses = {
            @ApiResponse(responseCode = "200", description = "Respuesta exitosa")
    })
    @GetMapping("/ping")
    ResponseEntity<String> ping();

    @Operation(summary = "Convertir datos en el microservicio", responses = {
            @ApiResponse(responseCode = "200", description = "Conversion exitosa"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida")
    })
    @PostMapping("/convert")
    ResponseEntity<String> convert(@RequestBody Map<String, Object> payload);
}