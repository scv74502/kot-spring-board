package org.example.assignment.member.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.example.assignment.common.dto.ApiResponse
import org.example.assignment.member.dto.MemberUpdateRequest
import org.example.assignment.member.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Tag(name = "로그인 후 사용 가능한 메소드들")
@RequestMapping("/api/member")
@PreAuthorize("hasAuthority('USER')")
@RestController
class MemberController (
    private val memberService: MemberService
) {
    @Operation(summary = "회원의 자기 자신 정보 조회하기")
    @GetMapping
    fun getMemberInfo(@AuthenticationPrincipal user: User) =
        ApiResponse.success(memberService.getMemberInfo(user.username))

    @Operation(summary = "회원의 삭제하기")
    @DeleteMapping
    fun deleteMember(@AuthenticationPrincipal user: User) =
        ApiResponse.success(memberService.deleteMember(user.username.toLong()))

    @Operation(summary = "회원의 자기 정보 수정하기")
    @PutMapping
    fun updateMember(@AuthenticationPrincipal user: User, @RequestBody request: MemberUpdateRequest) =
        ApiResponse.success(memberService.updateMember(user.username.toLong(), request))
}