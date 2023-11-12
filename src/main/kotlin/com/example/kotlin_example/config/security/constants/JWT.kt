package com.example.kotlin_example.config.security.constants // ktlint-disable package-name

enum class JWT(
    val value: String,
) {
    ACCESS_TOKEN("accessToken"), REFRESH_TOKEN("refreshToken"), AUTHORIZATION("Authorization"),
    BARREAR_PREFIX("Barrer "),
}
