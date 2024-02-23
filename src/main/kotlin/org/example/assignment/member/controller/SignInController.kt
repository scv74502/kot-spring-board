package org.example.assignment.member.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.example.assignment.common.dto.ApiResponse
import org.example.assignment.member.dto.SignInRequest
import org.example.assignment.member.dto.SignUpRequest
import org.example.assignment.member.service.SignService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "회원 가입 및 로그인 API")
@RestController
@RequestMapping
class SignInController(
  private val singService: SignService
) {
    @Operation(summary = "회원가입")
    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: SignUpRequest) = ApiResponse.success(singService.registerMember(request))

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    fun signIn(@RequestBody request: SignInRequest) = ApiResponse.success(singService.signIn(request))
}