package com.example.kotlin_example.config

import com.example.kotlin_example.domain.Member
import com.example.kotlin_example.domain.Role
import com.example.kotlin_example.domain.Role.USER
import com.example.kotlin_example.repository.MemberRepository
import io.github.serpro69.kfaker.faker
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener

@Configuration
class InitData(
    private val memberRepository: MemberRepository
) {
    val faker = faker {  }

    @EventListener(ApplicationReadyEvent::class)
    private fun init(){

        val member = Member(
            email = faker.internet.safeEmail(),
            password = "1234",
            role = USER
        )
        memberRepository.save(member)
    }
}