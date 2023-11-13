package com.example.kotlin_example.api // ktlint-disable package-name

import com.example.kotlin_example.api.common.Log
import com.example.kotlin_example.service.MemberService
import jakarta.servlet.http.HttpSession
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val memberService: MemberService,
) : Log() {
    @GetMapping("/login")
    fun login(session: HttpSession) {
        session.setAttribute("principal", "pass")
    }
}
