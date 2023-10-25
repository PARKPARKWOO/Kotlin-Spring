package com.example.kotlin_example.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.ManyToOne

@Entity
class Comment(
    content: String,
    post: Post
) : BaseEntity() {
    @Column(nullable = false)
    var content: String = content
        private set
    @ManyToOne(fetch = LAZY, targetEntity = Post::class)
    var post: Post = post
        private set
}