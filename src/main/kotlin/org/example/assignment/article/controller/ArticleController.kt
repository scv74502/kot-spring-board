package org.example.assignment.article.controller

import io.swagger.v3.oas.annotations.Operation
import org.example.assignment.article.dto.ArticleUpdateRequestDto
import org.example.assignment.article.dto.ArticleWriteRequestDto
import org.example.assignment.article.service.ArticleService
import org.example.assignment.common.dto.ApiResponse
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/article")
@RestController
class ArticleController (
    private val articleService: ArticleService
) {

//    @PostMapping
//    @PreAuthorize("hasAuthority('USER')")
//    fun test(@AuthenticationPrincipal user:User){
//        println(user.toString())
//    }

    @Operation(summary = "회원정보 바탕으로 기사 작성하기")
    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    fun writeArticle(@AuthenticationPrincipal user: User, @RequestBody request:ArticleWriteRequestDto) =
        ApiResponse.success(articleService.writeArticle(user.username.toLong(), request))

    @Operation(summary = "회원이 자신이 작성한 게시글 수정하기")
    @PutMapping
    @PreAuthorize("hasAuthority('USER')")
    fun updateMember(@AuthenticationPrincipal user: User, @RequestBody request: ArticleUpdateRequestDto) =
        ApiResponse.success(articleService.updateArticle(user.username.toLong(), request))
}