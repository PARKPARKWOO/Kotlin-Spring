package com.example.kotlin_example.config

import com.example.kotlin_example.config.security.constants.JWT
import com.example.kotlin_example.config.security.constants.JWT.AUTHORIZATION
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.models.OpenAPI
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(
    info = Info(
        title = "swagger",
        description = "swagger를 설명한다.",
        version = "v1",
    )
)
@SecurityScheme(
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "jwt",
    `in` = SecuritySchemeIn.HEADER,
    name = "Authorization"
)
class SwaggerConfig {
}