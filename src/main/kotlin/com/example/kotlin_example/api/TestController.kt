package com.example.kotlin_example.api // ktlint-disable package-name

import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.headers.Header
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    @GetMapping("/health")
    fun healthTest(): String {
        return "hello"
    }
}
