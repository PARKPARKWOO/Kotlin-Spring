package com.example.kotlin_example.service.impl

import com.example.kotlin_example.config.security.PrincipalDetails
import com.example.kotlin_example.domain.member.dto.LoginDto
import com.example.kotlin_example.repository.MemberRepository
import com.example.kotlin_example.service.AuthService
import com.example.kotlin_example.service.MemberService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val memberRepository: MemberRepository,
    private val memberService: MemberService
):AuthService {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)
    override fun loadUserByUsername(email: String): UserDetails {
         log.info("AuthService")
        val member = memberRepository.findByEmail(email)
        return PrincipalDetails(member!!)
    }
}