package com.example.kotlin_example.repository

import com.example.kotlin_example.domain.post.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long> {

}