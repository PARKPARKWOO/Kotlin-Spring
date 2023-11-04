package com.example.kotlin_example.api

import com.example.kotlin_example.api.common.BaseController
import com.example.kotlin_example.service.MemberService
import com.example.kotlin_example.util.Response
import jakarta.servlet.http.HttpSession
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val memberService: MemberService
) : BaseController() {
    @GetMapping("/login")
    fun login(session: HttpSession){
        session.setAttribute("principal", "pass")
    }
}