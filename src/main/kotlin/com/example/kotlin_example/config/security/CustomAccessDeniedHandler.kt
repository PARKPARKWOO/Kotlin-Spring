package com.example.kotlin_example.config.security

import com.example.kotlin_example.api.common.Log
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler

// ktlint-disable package-name

class CustomAccessDeniedHandler() : AccessDeniedHandler, Log() {

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException?,
    ) {
        log.info("accessDeniedHandler")
        response.sendError(HttpServletResponse.SC_FORBIDDEN)
    }
}
