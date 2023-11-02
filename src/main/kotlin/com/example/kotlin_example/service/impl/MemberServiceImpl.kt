package com.example.kotlin_example.service.impl

import com.example.kotlin_example.domain.member.Member
import com.example.kotlin_example.domain.member.dto.MemberResponse
import com.example.kotlin_example.domain.member.dto.MemberSaveRequest
import com.example.kotlin_example.domain.member.dto.toEntity
import com.example.kotlin_example.error.ErrorResponse
import com.example.kotlin_example.error.ErrorResponse.DUPLICATE_EMAIL
import com.example.kotlin_example.error.exception.DuplicateEmailException
import com.example.kotlin_example.error.exception.MemberNotFoundException
import com.example.kotlin_example.repository.MemberRepository
import com.example.kotlin_example.service.MemberService
import com.example.kotlin_example.util.Response
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberServiceImpl(
    private val memberRepository: MemberRepository
) : MemberService {

    @Override
    override fun findAll(): List<Member>{
        return memberRepository.findAll()
    }
    @Override
    override fun findById(memberId: Long): Member{
        return memberRepository.findById(memberId).orElseThrow { throw MemberNotFoundException() }
    }

    @Override
    override fun findMemberResponseAll(): List<MemberResponse> {
        val findAll = findAll()
        return findAll.map {
            MemberResponse(
                id = it.id,
                email = it.email,
                password = it.password,
                role = it.role
            )
        }.toList()
    }

    @Override
    @Transactional
    override fun createMember(saveRequest: MemberSaveRequest): Long {
        validEmail(saveRequest.email!!)
        val memberEntity = saveRequest.toEntity()
        memberRepository.save(memberEntity)
        return memberEntity.id!!
    }
    private fun validEmail(email: String) {
        memberRepository.findByEmail(email)?.let {
            throw DuplicateEmailException(DUPLICATE_EMAIL)
        }
    }

    @Override
    @Transactional
    override fun deleteMember(memberId: Long) {
        val member = findById(memberId)
        memberRepository.delete(member)
    }

    override fun pagingMembers(page: Pageable): List<MemberResponse> {
        return memberRepository.findMembers(page).map {
            MemberResponse(
                id = it.id,
                email = it.email,
                password = it.password,
                role = it.role
            )
        }
    }
}