package com.example.kotlin_example.error.exception

import com.example.kotlin_example.error.ErrorResponse

sealed class PostException(
    open val errorResponse: ErrorResponse
):RuntimeException(errorResponse.message)

data class PostNotFoundException(
    override val errorResponse: ErrorResponse = ErrorResponse.POST_NOT_FOUND
):PostException(errorResponse)

data class PostCreateFailException(
    override val errorResponse: ErrorResponse
):PostException(errorResponse){
}