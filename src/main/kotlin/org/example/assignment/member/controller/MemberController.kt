package org.example.assignment.member.controller

import io.swagger.v3.oas.annotations.Operation
import org.example.assignment.common.dto.ApiResponse
import org.example.assignment.member.dto.MemberUpdateRequest
import org.example.assignment.member.service.MemberService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/member")
@PreAuthorize("hasAuthority('USER')")
@RestController
class MemberController (
    private val memberService: MemberService
) {
    @Operation(summary = "회원의 자기 자신 정보 조회하기")
    @PostMapping
    fun getMemberInfo(@AuthenticationPrincipal user: User) =
        ApiResponse.success(memberService.getMemberInfo(user.username.toLong()))

    @Operation(summary = "회원의 삭제하기")
    @DeleteMapping
    @Transactional
    fun deleteMember(@AuthenticationPrincipal user: User) =
        ApiResponse.success(memberService.deleteMember(user.username.toLong()))

    @Operation(summary = "회원의 자기 정보 수정하기")
    @PutMapping
    fun updateMember(@AuthenticationPrincipal user: User, @RequestBody request: MemberUpdateRequest) =
        ApiResponse.success(memberService.updateMember(user.username.toLong(), request))
}