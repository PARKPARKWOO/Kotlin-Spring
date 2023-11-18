package com.example.kotlin_example.config.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint

class CustomAuthenticationEntryPoint(): AuthenticationEntryPoint {
    private val log: Logger = LoggerFactory.getLogger(this::class.java.simpleName)
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        log.info("commence 요청 들어옴")
         response.sendError(HttpServletResponse.SC_FORBIDDEN)
    }
}