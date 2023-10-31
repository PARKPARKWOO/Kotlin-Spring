package com.example.kotlin_example.repository

import com.example.kotlin_example.domain.member.Member
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderContext
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository:JpaRepository<Member, Long> {
}

interface MemberRepositoryCustom {
}

class MemberRepositoryCustomImpl(
    private val queryFactory: SpringDataQueryFactory
) : MemberRepositoryCustom {

}