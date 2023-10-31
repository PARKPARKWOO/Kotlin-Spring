package com.example.kotlin_example

import com.example.kotlin_example.domain.BaseEntity
import com.example.kotlin_example.repository.MemberRepositoryCustomImpl
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinExampleApplication

fun main(args: Array<String>) {
    runApplication<KotlinExampleApplication>(*args)
}
