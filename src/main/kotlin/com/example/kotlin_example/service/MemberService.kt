package com.example.kotlin_example.service

import com.example.kotlin_example.domain.member.Member
import com.example.kotlin_example.domain.member.dto.MemberResponse
import com.example.kotlin_example.domain.member.dto.LoginDto
import org.springframework.data.domain.Pageable

interface MemberService {
    fun findAll(): List<Member>
    fun findById(memberId: Long): Member
    fun findByEmail(email: String): Member
    fun findMemberResponseAll(): List<MemberResponse>
    fun createMember(saveRequest: LoginDto): Long
    fun deleteMember(memberId: Long)
    fun pagingMembers(page: Pageable): List<MemberResponse>
}