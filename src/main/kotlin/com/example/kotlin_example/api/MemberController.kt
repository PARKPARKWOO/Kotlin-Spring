package com.example.kotlin_example.api

import com.example.kotlin_example.domain.member.Member
import com.example.kotlin_example.domain.member.dto.MemberResponse
import com.example.kotlin_example.service.MemberService
import com.example.kotlin_example.util.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/member")
class MemberController(
    private val memberService: MemberService,

) {
    val log: Logger = LoggerFactory.getLogger(this::class.java)
    @GetMapping("/all")
    fun findAll():Response<List<MemberResponse>> = Response(OK, "멤버 전체 목록", memberService.findMemberResponseAll())

    @GetMapping("/{memberId}")
    fun findMember(@PathVariable("memberId") memberId :Long):Response<MemberResponse>{
        val member = memberService.findById(memberId)
        val response = MemberResponse(
            id = member.id,
            email = member.email,
            password = member.password,
            role = member.role
        )
        log.info(memberId.toString())
        return Response(OK, "$memberId 회원 정보", response)
    }

    @DeleteMapping("/{memberId}")
    fun deleteMember(@PathVariable("memberId") memberId: Long){
        memberService.deleteMember(memberId)
    }

    @GetMapping("/paging")
    fun pagingMembers(@PageableDefault(size = 10) page: Pageable):Response<List<MemberResponse>> {
        return Response(OK, "페이징", memberService.pagingMembers(page))
    }
}