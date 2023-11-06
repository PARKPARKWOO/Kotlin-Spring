package com.example.kotlin_example.util

import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@ExtendWith(MockKExtension::class)
class SecurityTest {

    @Test
    @DisplayName("password 를 암호화 한다")
    fun passwordEncryptTest() {
        val encoder = BCryptPasswordEncoder()
        val encode = encoder.encode("1234")
        Assertions.assertNotEquals(encode, "1234")
        val upgradeEncoding = encoder.upgradeEncoding(encode)
        Assertions.assertEquals(upgradeEncoding, "1234")
    }
}