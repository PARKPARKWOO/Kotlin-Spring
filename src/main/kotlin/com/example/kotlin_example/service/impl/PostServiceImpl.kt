package com.example.kotlin_example.service.impl

import com.example.kotlin_example.domain.post.Post
import com.example.kotlin_example.domain.post.dto.PostCreateRequest
import com.example.kotlin_example.domain.post.dto.PostResponse
import com.example.kotlin_example.domain.post.dto.toEntity
import com.example.kotlin_example.error.ErrorResponse
import com.example.kotlin_example.error.exception.PostCreateFailException
import com.example.kotlin_example.repository.PostRepository
import com.example.kotlin_example.service.MemberService
import com.example.kotlin_example.service.PostService
import com.example.kotlin_example.util.Response
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostServiceImpl(
     private val postRepository: PostRepository,
     private val memberService: MemberService
): PostService {
    @Override
    @Transactional
    override fun createPost(postRequest: PostCreateRequest): Long {
        validPost(postRequest)
        val member = memberService.findById(postRequest.memberId)
        val postEntity = postRequest.toEntity(postRequest, member)
        postRepository.save(postEntity)
        return postEntity.id ?: throw PostCreateFailException(ErrorResponse.POST_CREATE_TITLE_EMPTY)
    }
    fun validPost(postRequest: PostCreateRequest) {
        if (postRequest.title.isEmpty()) {
            throw PostCreateFailException(ErrorResponse.POST_CREATE_TITLE_EMPTY)
        }
    }
    @Override
    override fun getPost(postId: Long): Post {
        return postRepository.findById(postId).orElseThrow { throw PostCreateFailException(ErrorResponse.POST_NOT_FOUND) }
    }

    @Override
    @Transactional
    override fun deletePost(post: Post) {
        postRepository.delete(post)
    }

    @Override
    override fun pagingPost(page: Pageable): List<PostResponse> {
        return postRepository.findPosts(page).map {
            PostResponse(
                id = it.id!!,
                title = it.title,
                content = it.content,
                memberId = it.member.id!!
            )
        }
    }

}