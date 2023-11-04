package com.example.kotlin_example.repository

import com.example.kotlin_example.domain.member.Member
import com.example.kotlin_example.domain.post.Post
import com.linecorp.kotlinjdsl.query.spec.ExpressionOrderSpec
import com.linecorp.kotlinjdsl.query.spec.predicate.PredicateSpec
import com.linecorp.kotlinjdsl.querydsl.expression.column
import com.linecorp.kotlinjdsl.querydsl.from.fetch
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.listQuery
import com.linecorp.kotlinjdsl.spring.data.singleQuery
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface PostRepository : JpaRepository<Post, Long>, PostRepositoryCustom {

}

interface PostRepositoryCustom {
    fun findPosts(page: Pageable): List<Post>
    fun findFetchPost(postId : Long): Optional<Post>
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

    override fun findFetchPost(postId: Long): Optional<Post> {
        return Optional.of(queryFactory.singleQuery {
            select(entity(Post::class))
            from(entity(Post::class))
            fetch(Post::member)
            where(column(Post::id).equal(postId))
        })
    }


}

