package com.example.kotlin_example.api

import com.example.kotlin_example.domain.post.dto.PostCreateRequest
import com.example.kotlin_example.domain.post.dto.PostResponse
import com.example.kotlin_example.service.PostService
import com.example.kotlin_example.util.Response
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/post")
class PostController(
    private val postService: PostService,
) {
    @PostMapping("/create")
    fun createPost(@RequestBody postRequest: PostCreateRequest): Response<Long> {
        val postId = postService.createPost(postRequest)
        return Response(201, "게시글 생성 완료", postId)
    }

    @GetMapping("/{id}")
    fun selectPost(@PathVariable("id") postId: Long): Response<PostResponse> {
        val post = postService.getPost(postId)
        val response = PostResponse(
            id = post.id!!,
            title = post.title,
            content = post.content,
            memberId = post.member.id!!
        )
        return Response(200, "게시글 조회 완료", response)
    }
}