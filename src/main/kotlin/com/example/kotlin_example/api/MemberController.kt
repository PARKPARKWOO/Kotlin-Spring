package com.example.kotlin_example.api

import com.example.kotlin_example.domain.Member
import com.example.kotlin_example.service.MemberService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping("/all")
    fun findAll():MutableList<Member> = memberService.findAll()


}