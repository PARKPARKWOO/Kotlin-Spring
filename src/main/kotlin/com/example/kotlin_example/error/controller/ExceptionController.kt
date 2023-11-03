package com.example.kotlin_example.error.controller

import com.example.kotlin_example.api.common.BaseController
import com.example.kotlin_example.error.ErrorResponse
import com.example.kotlin_example.error.FieldErrorResponse
import com.example.kotlin_example.error.exception.MemberException
import com.example.kotlin_example.error.exception.PostException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionController(): BaseController() {
    @ExceptionHandler(value = [MemberException::class])
    fun memberException(e: MemberException): ResponseEntity<ErrorResponse> {
        log.error(e.message)
        return ResponseEntity.status(e.errorResponse.httpStatus).body(e.errorResponse)
    }

    @ExceptionHandler(value = [PostException::class])
    fun postException(e: PostException): ResponseEntity<ErrorResponse> {
        log.error(e.message)
        return ResponseEntity.status(e.errorResponse.httpStatus).body(e.errorResponse)
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun validException(e: MethodArgumentNotValidException): ResponseEntity<FieldErrorResponse>{
        log.error(e.message)
        val of = FieldErrorResponse.FieldError.of(e.bindingResult)
        val filedErrorResponse = FieldErrorResponse(e.statusCode, e.message, of)
        return ResponseEntity.status(e.statusCode).body(filedErrorResponse)
    }
}