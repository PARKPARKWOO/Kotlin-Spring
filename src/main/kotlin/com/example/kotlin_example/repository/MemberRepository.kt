package com.example.kotlin_example.repository

import com.example.kotlin_example.domain.member.Member
import com.linecorp.kotlinjdsl.dsl.jpql.jpql
import com.linecorp.kotlinjdsl.query.spec.ExpressionOrderSpec
import com.linecorp.kotlinjdsl.querydsl.expression.column
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderContext
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.listQuery
import org.slf4j.LoggerFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface MemberRepository:JpaRepository<Member, Long>, MemberRepositoryCustom {
}

interface MemberRepositoryCustom {
    fun fineMembers(): List<Member>
}

class MemberRepositoryCustomImpl(
    private val queryFactory: SpringDataQueryFactory
) : MemberRepositoryCustom {
    override fun fineMembers(): List<Member> {
        return queryFactory.listQuery<Member> {
            select(entity(Member::class))
            from(entity(Member::class))
            orderBy(ExpressionOrderSpec(column(Member::id), false))
        }
    }
}