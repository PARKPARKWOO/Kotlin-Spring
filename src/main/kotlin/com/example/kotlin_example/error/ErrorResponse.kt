package com.example.kotlin_example.error

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*

enum class ErrorResponse(val httpStatus: HttpStatus, val message: String){
    // Member
    MEMBER_NOT_FOUND(NOT_FOUND, "Member Not Found"),
    DUPLICATE_EMAIL(BAD_REQUEST, "Duplicate email"),

    // Post
    POST_NOT_FOUND(NOT_FOUND, "Post Not Found"),
    POST_CREATE_TITLE_EMPTY(BAD_REQUEST, "Post Create Title Empty"),

    // JWT
    JWT_NOT_FOUND(FORBIDDEN, "JWT TOKEN EMPTY")
}