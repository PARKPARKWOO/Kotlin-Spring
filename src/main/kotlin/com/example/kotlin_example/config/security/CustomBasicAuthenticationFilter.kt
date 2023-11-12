package com.example.kotlin_example.config.security // ktlint-disable package-name

import com.example.kotlin_example.config.security.constants.JWT
import com.example.kotlin_example.config.security.constants.JWT.AUTHORIZATION
import com.example.kotlin_example.service.MemberService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

class CustomBasicAuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private val jwtMapper: JwtMapper,
    private val memberService: MemberService,
) : BasicAuthenticationFilter(authenticationManager) {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        log.info("권한이나 인증요청이 들어옴 ${AUTHORIZATION.name}")
        val header = request.getHeader(AUTHORIZATION.name)
        val token = if (header != null && header.length > JWT.BARREAR_PREFIX.name.length) {
            header.substring(JWT.BARREAR_PREFIX.name.length)
        } else {
            chain.doFilter(request, response)
            return
        }
        val email = jwtMapper.getMemberEmail(token)
        val member = memberService.findByEmail(email)

        val principalDetails = PrincipalDetails(member)
        val authentication: Authentication = UsernamePasswordAuthenticationToken(
            principalDetails,
            principalDetails.password,
        )
        SecurityContextHolder.getContext().authentication = authentication
        chain.doFilter(request, response)
    }

    override fun onSuccessfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authResult: Authentication?,
    ) {
        super.onSuccessfulAuthentication(request, response, authResult)
    }
}
