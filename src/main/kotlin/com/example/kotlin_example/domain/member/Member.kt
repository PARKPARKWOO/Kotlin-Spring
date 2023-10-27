package com.example.kotlin_example.domain.member

import com.example.kotlin_example.domain.BaseEntity
import jakarta.persistence.*

@Entity
class Member(
    email: String,
    password: String,
    role: Role
) : BaseEntity() {
    var email: String = email
        private set
    var password: String = password
        private set
    @Enumerated(EnumType.STRING)
    var role: Role = role
        private set
}

enum class Role {
    ADMIN, USER
}