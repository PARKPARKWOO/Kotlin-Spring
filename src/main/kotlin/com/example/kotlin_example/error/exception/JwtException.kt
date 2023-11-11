package com.example.kotlin_example.error.exception

import com.example.kotlin_example.error.ErrorResponse

sealed class JwtException(
    open val errorResponse:ErrorResponse
):RuntimeException(errorResponse.message)

data class JwtNotFoundException(
    override val errorResponse: ErrorResponse
):JwtException(errorResponse)