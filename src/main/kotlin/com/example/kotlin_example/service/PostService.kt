package com.example.kotlin_example.service

import com.example.kotlin_example.domain.post.Post
import com.example.kotlin_example.domain.post.dto.PostCreateRequest

interface PostService {
    fun createPost(postRequest: PostCreateRequest):Long
    fun getPost(postId: Long):Post
    fun deletePost(post: Post)
}