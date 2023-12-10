package com.example.kotlin_example.config.security // ktlint-disable package-name

import com.example.kotlin_example.config.security.constants.JWT
import com.example.kotlin_example.config.security.constants.JWT.AUTHORIZATION
import com.example.kotlin_example.config.security.constants.Time.ACCESS_TOKEN_TIME
import com.example.kotlin_example.domain.member.dto.LoginDto
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class CustomAuthenticationFilter(
    private val objectMapper: ObjectMapper,
    private val jwtMapper: JwtMapper,
) : UsernamePasswordAuthenticationFilter() {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val loginDto = objectMapper.readValue(request?.inputStream, LoginDto::class.java)
        log.info("login 요청 $loginDto")

        val token = UsernamePasswordAuthenticationToken(loginDto.email, loginDto.password)

        return authenticationManager.authenticate(token)
    }

    override fun obtainPassword(request: HttpServletRequest?): String? {
        return super.obtainPassword(request)
    }

    override fun obtainUsername(request: HttpServletRequest?): String? {
        return super.obtainUsername(request)
    }

    override fun setDetails(request: HttpServletRequest?, authRequest: UsernamePasswordAuthenticationToken?) {
        super.setDetails(request, authRequest)
    }

    override fun setUsernameParameter(usernameParameter: String?) {
        super.setUsernameParameter(usernameParameter)
    }

    override fun setPasswordParameter(passwordParameter: String?) {
        super.setPasswordParameter(passwordParameter)
    }

    override fun setPostOnly(postOnly: Boolean) {
        super.setPostOnly(postOnly)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?,
    ) {
        log.info("로그인 완료되어서 jwt 토큰 만들어서 response")
        val principalDetails = authResult?.principal as PrincipalDetails
        val accessToken = jwtMapper.generateAccessToken(principalDetails, ACCESS_TOKEN_TIME)
        log.info("jwt token = $accessToken")
        response?.addHeader(AUTHORIZATION.name, JWT.BARREAR_PREFIX.value + accessToken)
    }
}
