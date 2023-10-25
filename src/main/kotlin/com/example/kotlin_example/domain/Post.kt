package com.example.kotlin_example.domain

import jakarta.persistence.*
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.validation.constraints.NotNull

@Entity
class Post (
    title:String,
    content: String
): BaseEntity(){

    @Column(nullable = false)
    var title: String = title
        private set
    var content: String = content
        private set
}
