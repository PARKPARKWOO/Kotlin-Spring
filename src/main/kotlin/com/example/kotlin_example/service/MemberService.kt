package com.example.kotlin_example.service

import com.example.kotlin_example.domain.Member

interface MemberService {
    fun findAll(): MutableList<Member>
}