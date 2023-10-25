package com.example.kotlin_example.service.impl

import com.example.kotlin_example.domain.Member
import com.example.kotlin_example.repository.MemberRepository
import com.example.kotlin_example.service.MemberService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberServiceImpl(
    private val memberRepository: MemberRepository
) : MemberService {

    override fun findAll(): MutableList<Member> = memberRepository.findAll()

    
}