package com.example.kotlin_example.api

import com.example.kotlin_example.domain.Member
import com.example.kotlin_example.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.function.EntityResponse

@RestController
@RequestMapping("/api/members")
class MemberController(
    private val memberService: MemberService,

) {
    @GetMapping("/all")
    fun findAll():ResponseEntity<MutableList<Member>> = ResponseEntity.ok().body(memberService.findAll())

}