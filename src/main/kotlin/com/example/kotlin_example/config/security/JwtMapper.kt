package com.example.kotlin_example.config.security

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt

class JwtMapper(
    val log:Logger = LoggerFactory.getLogger(JwtMapper::class.java)
) {

    fun generateAccessToken() {

    }
}