package com.project.redpontis.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Milestones API", version = "1.0", description = "Documentación del API"),
tags = {
        @Tag(name = "Task", description = "Services Task"),
        @Tag(name = "User", description = "Gestion de usuarios"),
        @Tag(name = "Login", description = "Autenticacion de usuarios")
    }
)
@SecurityScheme(
    name = "bearerAuth",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}