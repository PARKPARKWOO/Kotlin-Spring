package com.example.kotlin_example.util

import org.springframework.http.HttpStatus


data class Response<T>(
    val code: HttpStatus,
    val message: String,
    val data: T
)
