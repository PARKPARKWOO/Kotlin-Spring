package com.example.kotlin_example.util


data class Response<T>(
    val code: Int,
    val message: String,
    val data: T
)
