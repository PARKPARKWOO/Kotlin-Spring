package com.example.kotlin_example.api

import com.example.kotlin_example.api.common.BaseController
import com.example.kotlin_example.domain.post.dto.PostCreateRequest
import com.example.kotlin_example.domain.post.dto.PostResponse
import com.example.kotlin_example.service.PostService
import com.example.kotlin_example.util.Response
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.DeleteMapping
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
):BaseController() {
    @PostMapping("/create")
    fun createPost(@RequestBody postRequest: PostCreateRequest): Response<Long> {
        val postId = postService.createPost(postRequest)
        return Response(CREATED, "게시글 생성 완료", postId)
    }

    @GetMapping("/paging")
    fun pagingPost(@PageableDefault(size = 10) page: Pageable): Response<List<PostResponse>> {
        return Response(OK, "페이징", postService.pagingPost(page))
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
        return Response(OK, "$postId 번 게시글 조회 완료", response)
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable("id") postId: Long ):Response<Unit>{
        val post = postService.getPost(postId)
        postService.deletePost(post)
        return Response(OK, "$postId 번 게시글 삭제 완료", Unit)
    }
}