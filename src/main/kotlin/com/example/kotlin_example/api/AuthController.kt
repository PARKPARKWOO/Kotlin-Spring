package com.example.kotlin_example.api

import com.example.kotlin_example.api.common.BaseController
import com.example.kotlin_example.service.MemberService
import jakarta.servlet.http.HttpSession
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val memberService: MemberService
) : BaseController() {
    @PostMapping("/login")
    fun login(session: HttpSession){
        session.setAttribute("principal", "pass")
    }
}