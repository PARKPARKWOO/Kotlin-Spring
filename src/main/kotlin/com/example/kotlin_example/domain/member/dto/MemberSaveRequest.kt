package com.example.kotlin_example.domain.member.dto

import com.example.kotlin_example.domain.member.Member
import com.example.kotlin_example.domain.member.Role

data class MemberSaveRequest(
    val email: String,
    val password: String,
    val role: Role
)

fun MemberSaveRequest.toEntity(): Member {
    return Member(
        email = email,
        password = password,
        role = role
    )
}

data class MemberResponse(
    val id: Long,
    val email: String,
    val password: String,
    val role: Role
)