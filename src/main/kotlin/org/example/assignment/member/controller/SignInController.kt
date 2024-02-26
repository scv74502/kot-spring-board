package org.example.assignment.member.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.extern.slf4j.Slf4j
import org.example.assignment.common.dto.ApiResponse
import org.example.assignment.common.logger.CommonLogger
import org.example.assignment.member.dto.SignInRequest
import org.example.assignment.member.dto.SignUpRequest
import org.example.assignment.member.service.SignService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/sign")
class SignInController(
  private val singService: SignService
) {
    companion object: CommonLogger() {}
    @Operation(summary = "logger test")
    @GetMapping("/log-test")
    fun logTest(){
        logger.info("test")
    }

    @Operation(summary = "회원가입")
    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: SignUpRequest) = ApiResponse.success(singService.registerMember(request))

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    fun signIn(@RequestBody request: SignInRequest) = ApiResponse.success(singService.signIn(request))
}