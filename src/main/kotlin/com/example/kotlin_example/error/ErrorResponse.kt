package com.example.kotlin_example.error

enum class ErrorResponse(val httpStatus: Int, val message: String){
    // Member
    MEMBER_NOT_FOUND(404, "Member Not Found"),


    // Post
    POST_NOT_FOUND(404, "Post Not Found"),
    POST_CREATE_TITLE_EMPTY(400, "Post Create Title Empty"),
}