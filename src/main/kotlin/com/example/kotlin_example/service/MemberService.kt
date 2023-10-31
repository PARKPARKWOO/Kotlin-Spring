package com.example.kotlin_example.service

import com.example.kotlin_example.domain.member.Member
import com.example.kotlin_example.domain.member.dto.MemberResponse

interface MemberService {
    fun findAll(): List<Member>
    fun findById(memberId: Long): Member
    fun findMemberResponseAll(): List<MemberResponse>
    fun createMember(): Long
    fun deleteMember(memberId: Long)
}