package com.example.kotlin_example.api // ktlint-disable package-name

import com.example.kotlin_example.api.common.Log
import com.example.kotlin_example.api.response.MemberJoinResponse
import com.example.kotlin_example.config.security.JwtMapper
import com.example.kotlin_example.domain.member.dto.LoginDto
import com.example.kotlin_example.domain.member.dto.MemberResponse
import com.example.kotlin_example.service.MemberService
import com.example.kotlin_example.util.Response
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/member")
class MemberController(
    private val memberService: MemberService,
    private val jwtMapper: JwtMapper,
) : Log() {
    @GetMapping("/all")
    fun findAll(): Response<List<MemberResponse>> = Response(OK, "멤버 전체 목록", memberService.findMemberResponseAll())

    @GetMapping("/{memberId}")
    fun findMember(@PathVariable("memberId") memberId: Long): Response<MemberResponse> {
        val member = memberService.findById(memberId)
        val response = MemberResponse(
            id = member.id,
            email = member.email,
            password = member.password,
            role = member.role,
        )
        return Response(OK, "$memberId 회원 정보", response)
    }

    @DeleteMapping("/{memberId}")
    fun deleteMember(@PathVariable("memberId") memberId: Long) {
        memberService.deleteMember(memberId)
    }

    @GetMapping("/paging")
    fun pagingMembers(@PageableDefault(size = 10) page: Pageable): Response<List<MemberResponse>> {
        return Response(OK, "페이징", memberService.pagingMembers(page))
    }

    @PostMapping("/login")
    // JWT 필수
    @Parameters(
        Parameter(
            name = "Authorization",
            `in` = ParameterIn.HEADER,
            required = true,

        )
    )
    fun createMember(
        @Valid @RequestBody
        saveRequest: LoginDto,
    ): Response<MemberJoinResponse> {
        val memberId = memberService.createMember(saveRequest)
        val member = memberService.findById(memberId)
        val generateAccessToken = jwtMapper.generateAccessToken(member, 11111111L)
        val response = MemberJoinResponse(
            id = memberId,
            accessToken = generateAccessToken
        )
        return Response(CREATED, "회원 가입 성공", response)
    }
}
