package com.example.kotlin_example.util

import com.example.kotlin_example.config.security.JwtMapper
import com.example.kotlin_example.config.security.PrincipalDetails
import com.example.kotlin_example.domain.member.Member
import com.example.kotlin_example.domain.member.Role
import io.mockk.MockKException
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks

@ExtendWith(MockKExtension::class)
class JwtTest {
    @InjectMocks
    private lateinit var jwtManager: JwtMapper

    @BeforeEach
    fun setUp() {
        jwtManager = JwtMapper()
        // Use reflection to set a private lateinit var if needed
        val secretKeyField = JwtMapper::class.java.getDeclaredField("secretKey")
        secretKeyField.isAccessible = true
        secretKeyField.set(jwtManager, "your_test_secret_key")
    }


    @Test
    @DisplayName("JWT 토큰을 생성 한다.")
    fun generateJwtTest() {
        val member = Member("email", "pass", Role.USER)
        val details = PrincipalDetails(member)

        val accessToken = jwtManager.generateAccessToken(details)

        val email = jwtManager.getMemberEmail(accessToken)
        println(accessToken)
        Assertions.assertEquals(email, member.email)
    }
}