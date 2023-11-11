package com.example.kotlin_example.config.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.kotlin_example.config.security.constants.Time
import com.example.kotlin_example.config.security.constants.Time.ACCESS_TOKEN_TIME
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtMapper(
) {
    private val log:Logger = LoggerFactory.getLogger(this::class.java)

    @Value("\${custom.jwt.secret}")
    private lateinit var secretKey:String
    fun generateAccessToken(principal: PrincipalDetails, ttl: Long): String {
        return JWT.create()
            .withSubject(principal.username)
            .withExpiresAt(Date( System.nanoTime() + ttl))
            .withClaim("email", principal.username)
            .withClaim("password", principal.password)
            .sign(Algorithm.HMAC512(secretKey))
    }

    fun getMemberEmail(token: String):String {
        return JWT.require(Algorithm.HMAC512(secretKey)).build()
            .verify(token).getClaim("email").asString()
    }
}