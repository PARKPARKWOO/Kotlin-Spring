package com.example.kotlin_example.domain.member.dto

import com.example.kotlin_example.domain.member.Member
import com.example.kotlin_example.domain.member.Role
import io.swagger.v3.oas.annotations.Hidden
import jakarta.validation.constraints.NotNull

@Hidden
data class LoginDto(
    @field:NotNull(message = "email is Empty")
    val email: String?,
    val password: String?,
    val role: Role
)

fun LoginDto.toEntity(): Member {
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
