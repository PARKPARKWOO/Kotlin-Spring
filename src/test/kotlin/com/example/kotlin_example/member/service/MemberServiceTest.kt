package com.example.kotlin_example.member.service

import com.example.kotlin_example.domain.member.Role
import com.example.kotlin_example.domain.member.dto.MemberSaveRequest
import com.example.kotlin_example.service.MemberService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class MemberServiceTest(
    @Mock
    val memberService: MemberService

) {
    @Test
    @DisplayName("멤버를 생성한다")
    fun saveMember(){
        val request = MemberSaveRequest(
            email = "",
            password = "",
            role = Role.USER
        )


    }
}