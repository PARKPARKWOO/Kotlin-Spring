package com.example.kotlin_example.config.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import java.util.*

class JwtMapper(
) {
    private val log:Logger = LoggerFactory.getLogger(this::class.java)

    val ACCESS_TOKEN_VALIDATION_SECOND = 1000L * 60 * 30 // 30분


    val REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 60 * 24 * 14 // 14일

    @Value("\${custom.jwt.secret}")
    private lateinit var secretKey:String

    fun generateAccessToken(principal: PrincipalDetails): String {
        return JWT.create()
            .withSubject(principal.username)
            .withExpiresAt(Date( System.nanoTime() + ACCESS_TOKEN_VALIDATION_SECOND))
            .withClaim("email", principal.username)
            .withClaim("password", principal.password)
            .sign(Algorithm.HMAC512(secretKey))
    }

    fun getMemberEmail(token: String):String {
        return JWT.require(Algorithm.HMAC512(secretKey)).build()
            .verify(token).getClaim("email").asString()
    }
}