package com.example.kotlin_example.repository

import com.example.kotlin_example.domain.member.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository:JpaRepository<Member, Long> {
}