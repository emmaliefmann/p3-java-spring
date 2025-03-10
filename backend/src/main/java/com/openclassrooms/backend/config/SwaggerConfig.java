package com.openclassrooms.backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
  info =@Info(
    title = "ChâTop Rental API",
    description = "Project 3 for Fullstack developer degree, created by Emma Liefmann"
  ),
  security = @SecurityRequirement(name = "Bearer Authentication")
)
  @SecurityScheme(
    name = "Bearer Authentication",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
  )

public class SwaggerConfig {
}
