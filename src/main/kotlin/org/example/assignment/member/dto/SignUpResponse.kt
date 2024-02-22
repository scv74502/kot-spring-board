package org.example.assignment.member.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.example.assignment.common.status.Gender
import java.time.LocalDate

data class SignUpResponse(
        @Schema(description = "회원 고유 번호", example = "1")
        val id: Long,

        @Schema(description = "회원 아이디", example = "colabear754")
        val loginId: String,

        @Schema(description = "회원 비밀번호", example = "1234")
        var password: String,

        @Schema(description = "회원 이름", example = "콜라곰")
        val name: String,

        @Schema(description = "회원 나이", example = "27")
        val age: Int,

        @Schema(description = "회원 생일", example = "1996-07-13")
        val birthDate: LocalDate,

        @Schema(description = "성별", example = "남성")
        val gender: Gender,

        @Schema(description = "회원 이메일 주소", example = "scv74502@codecraft.co.kr")
        val email: String
)

data class SignInResponse(
        @Schema(description = "회원 아이디", example = "colabear754")
        val loginId: String,

        @Schema(description = "회원 비밀번호", example = "1234")
        var password: String
)


data class UpdateResponse(
        @Schema(description = "회원 기존 비밀번호", example = "aabb1234")
        var password: String,

        @Schema(description = "회원 새로 변경할 비밀번호", example = "aabb5678")
        var newPassword: String ?= null,

        @Schema(description = "회원 이름", example = "콜라곰")
        val name: String ?= null
)
