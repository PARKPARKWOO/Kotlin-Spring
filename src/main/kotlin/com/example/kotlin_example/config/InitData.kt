package com.example.kotlin_example.config

import com.example.kotlin_example.domain.member.Member
import com.example.kotlin_example.domain.member.Role.USER
import com.example.kotlin_example.domain.member.dto.LoginDto
import com.example.kotlin_example.domain.member.dto.toEntity
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
        val members = mutableListOf<Member>()
        for (i in 1.. 100){
            val generateMember = generateMember()
            members.add(generateMember)
        }
        memberRepository.saveAll(members)
    }
    private fun generateMember(): Member{
        return LoginDto(
            email = faker.internet.email(),
            password = "1234",
            role = USER
        ).toEntity()
    }
}