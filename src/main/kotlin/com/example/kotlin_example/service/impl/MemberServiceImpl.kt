package com.example.kotlin_example.service.impl

import com.example.kotlin_example.domain.member.Member
import com.example.kotlin_example.domain.member.dto.MemberResponse
import com.example.kotlin_example.domain.member.dto.toDto
import com.example.kotlin_example.error.exception.MemberNotFoundException
import com.example.kotlin_example.repository.MemberRepository
import com.example.kotlin_example.service.MemberService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberServiceImpl(
    private val memberRepository: MemberRepository
) : MemberService {

    @Override
    override fun findAll(): MutableList<Member> = memberRepository.findAll()
    @Override
    override fun findById(memberId: Long): MemberResponse {
        val memberEntity = memberRepository.findById(memberId)
        if (memberEntity.isEmpty) throw MemberNotFoundException()
        return memberEntity.get()
    }


}