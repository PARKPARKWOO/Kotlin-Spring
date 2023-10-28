package com.example.kotlin_example.domain.member.dto

import com.example.kotlin_example.domain.member.Member
import com.example.kotlin_example.domain.member.Role
import java.util.*

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
    val id: Long?,
    val email: String,
    val password: String,
    val role: Role
)

fun MemberResponse.toDto(member: Member):MemberResponse{
    return MemberResponse(
        id  = member.id,
        email = member.email,
        password = member.password,
        role = member.role
    )
}