package com.example.kotlin_example.config

import com.example.kotlin_example.config.security.constants.JWT
import com.example.kotlin_example.config.security.constants.JWT.AUTHORIZATION
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .components(
            Components().addSecuritySchemes(
                AUTHORIZATION_BEARER_SECURITY_SCHEME_NAME,
                AuthorizationBearerSecurityScheme,
            ),
        )
        .info(info())

    private fun info() = Info().title("Springdoc Swagger Info Title")
        .description("Springdoc Swagger Info Description")
        .version("3.0.1")
}
const val AUTHORIZATION_BEARER_SECURITY_SCHEME_NAME = "Authorization: Bearer ACCESS_TOKEN"
val AuthorizationBearerSecurityScheme: SecurityScheme = SecurityScheme()
    .name(AUTHORIZATION_BEARER_SECURITY_SCHEME_NAME)
    .type(SecurityScheme.Type.HTTP)
    .scheme("Bearer")
    .bearerFormat("JWT")