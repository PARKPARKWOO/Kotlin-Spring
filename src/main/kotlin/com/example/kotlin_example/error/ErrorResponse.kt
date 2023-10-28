package com.example.kotlin_example.error

enum class ErrorResponse(val httpStatus: Int, val message: String){
    // Member
    MEMBER_NOT_FOUND(404, "Member Not Found"),
}