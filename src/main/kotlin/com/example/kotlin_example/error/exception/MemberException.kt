package com.example.kotlin_example.error.exception

import com.example.kotlin_example.error.ErrorResponse
import com.example.kotlin_example.error.ErrorResponse.MEMBER_NOT_FOUND

sealed class MemberException(
    open val errorResponse: ErrorResponse
): RuntimeException(errorResponse.message)

data class MemberNotFoundException(
    override val errorResponse: ErrorResponse = MEMBER_NOT_FOUND
): MemberException(errorResponse)

data class DuplicateEmailException(
    override val errorResponse: ErrorResponse
): MemberException(errorResponse)