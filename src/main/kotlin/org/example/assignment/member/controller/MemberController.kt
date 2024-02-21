package org.example.assignment.member.controller

import org.example.assignment.common.dto.BaseResponse
import org.example.assignment.member.dto.MemberSignUpRequestDto
import org.example.assignment.member.entity.Member
import org.example.assignment.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RequestMapping("/api/member")
@RestController
class MemberController (
    private val memberService: MemberService
) {
    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid memberSignUpRequestDto: MemberSignUpRequestDto):
            ResponseEntity<Void> {
        val resultMsg: String = memberService.signUp(memberSignUpRequestDto)
        return ResponseEntity.

    }

    private fun MemberSignUpRequestDto.toModel() : Member =
        Member(
            id = null,
            loginId = this.loginId,
            password = this.password

        )
}