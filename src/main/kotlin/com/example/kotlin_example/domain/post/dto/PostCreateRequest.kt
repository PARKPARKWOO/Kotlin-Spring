package com.example.kotlin_example.domain.post.dto

import com.example.kotlin_example.domain.member.Member
import com.example.kotlin_example.domain.post.Post

data class PostCreateRequest(
    val memberId: Long,
    val title: String,
    val content: String
)

fun PostCreateRequest.toEntity(dto: PostCreateRequest, member:Member): Post {
    return Post(
        title = dto.title,
        content = dto.content,
        member = member
    )
}

data class PostResponse(
    val id: Long,
    val title: String,
    val content: String,
    val memberId: Long
)