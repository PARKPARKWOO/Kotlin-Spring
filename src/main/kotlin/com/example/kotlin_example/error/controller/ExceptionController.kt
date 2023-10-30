package com.example.kotlin_example.error.controller

import com.example.kotlin_example.error.ErrorResponse
import com.example.kotlin_example.error.exception.MemberException
import com.example.kotlin_example.error.exception.PostException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionController {
    @ExceptionHandler(value = [MemberException::class])
    fun memberException(e: MemberException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(e.errorResponse.httpStatus).body(e.errorResponse)
    }

    @ExceptionHandler(value = [PostException::class])
    fun postException(e: PostException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(e.errorResponse.httpStatus).body(e.errorResponse)
    }
}