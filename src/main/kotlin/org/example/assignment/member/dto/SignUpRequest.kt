package org.example.assignment.member.dto

import io.swagger.annotations.ApiModelProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.example.assignment.common.status.Gender
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class SignUpRequest(
        @Schema(description = "회원 아이디", example = "colabear754")
        @ApiModelProperty(value = "로그인 회원 아이디")
        val loginId: String,

        @Schema(description = "회원 비밀번호", example = "1234")
        var password: String,

        @Schema(description = "회원 이름", example = "콜라곰")
        val name: String,

        @Schema(description = "회원 email", example = "svret27@gmail.com")
        val email: String,

        @Schema(description = "회원 생년월일", example = "2024.02.19")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        val birthDate: LocalDate,

        @Schema(description = "회원 성별", example = "FEMALE")
        val gender: Gender
)

data class SignInRequest(
        @Schema(description = "회원 아이디", example = "colabear754")
        val loginId: String,
        @Schema(description = "회원 비밀번호", example = "1234")
        val password: String
)

data class MemberUpdateRequest(
        @Schema(description = "회원 이름", example = "콜라곰")
        val name: String,

        @Schema(description = "기존의 회원 비밀번호", example = "1234")
        var password: String,

        @Schema(description = "새로운 회원 비밀번호", example = "5678")
        val newPassword: String,

        @Schema(description = " 변경할 회원 이메일", example = "sdh51234@gmail.com")
        val email: String
)