package com.example.kotlin_example.domain.member.dto

import com.example.kotlin_example.domain.member.Member
import com.example.kotlin_example.domain.member.Role
import jakarta.validation.constraints.NotNull
import java.util.*

data class MemberSaveRequest(
    @field:NotNull(message = "email is Empty")
    val email: String?,
    val password: String?,
    val role: Role
)

fun MemberSaveRequest.toEntity(): Member {
    return Member(
        email = email?: "",
        password = password?: "",
        role = role
    )
}

data class MemberResponse(
    val id: Long?,
    val email: String,
    val password: String,
    val role: Role
)
