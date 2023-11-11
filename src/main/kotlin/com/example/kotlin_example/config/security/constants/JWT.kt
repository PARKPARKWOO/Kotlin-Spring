package com.example.kotlin_example.config.security.constants

enum class JWT(
    value: String
) {
    ACCESS_TOKEN("accessToken"), REFRESH_TOKEN("refreshToken"), AUTHORIZATION("Authorization"),
    BARREAR_PREFIX("Barrer ")
}