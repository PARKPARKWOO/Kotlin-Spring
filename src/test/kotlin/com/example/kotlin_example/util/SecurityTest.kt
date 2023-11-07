package com.example.kotlin_example.util

import com.example.kotlin_example.config.security.SecurityConfig
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@ExtendWith(MockKExtension::class)
class SecurityTest {

    private val securityConfig = mockk<SecurityConfig>()

    @Test
    fun `password 암호화 를 한다`() {
        every { securityConfig.passwordEncoder() } returns BCryptPasswordEncoder()
        val passwordEncoder = securityConfig.passwordEncoder()
        val purePassword = "1234"
        val encodePassword = passwordEncoder.encode(purePassword)

        Assertions.assertNotEquals(encodePassword, purePassword)
    }
}