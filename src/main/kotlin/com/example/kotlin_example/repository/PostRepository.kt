package com.example.kotlin_example.repository

import com.example.kotlin_example.domain.post.Post
import com.linecorp.kotlinjdsl.query.spec.ExpressionOrderSpec
import com.linecorp.kotlinjdsl.querydsl.expression.column
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.listQuery
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>, PostRepositoryCustom {

}

interface PostRepositoryCustom {
    fun findPosts(page: Pageable): List<Post>
}

class PostRepositoryCustomImpl(
    private val queryFactory: SpringDataQueryFactory
) : PostRepositoryCustom {
    override fun findPosts(page: Pageable): List<Post> {
        return queryFactory.listQuery {
            select(entity(Post::class))
            from(entity(Post::class))
            limit(page.pageSize)
            offset(page.offset.toInt())
            orderBy(ExpressionOrderSpec(column(Post::id), true))
        }
    }
}

