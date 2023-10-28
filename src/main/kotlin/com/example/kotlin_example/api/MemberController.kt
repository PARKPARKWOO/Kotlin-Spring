package com.example.kotlin_example.api

import com.example.kotlin_example.domain.member.Member
import com.example.kotlin_example.domain.member.dto.MemberResponse
import com.example.kotlin_example.service.MemberService
import com.example.kotlin_example.util.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
class MemberController(
    private val memberService: MemberService,

) {
    @GetMapping("/all")
    fun findAll():Response<MutableList<Member>> = Response(200, "멤버 전체 목록", memberService.findAll())

    @GetMapping("/{memberId}")
    fun findMember(@PathVariable("memberId") memberId :Long):Response<MemberResponse>{
        return Response(200, "$memberId 회원 정보", memberService.findById(memberId))
    }
}