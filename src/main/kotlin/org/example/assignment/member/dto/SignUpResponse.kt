package org.example.assignment.member.dto

import com.sun.org.apache.xpath.internal.operations.Bool
import io.swagger.v3.oas.annotations.media.Schema
import org.example.assignment.common.status.Gender
import org.example.assignment.member.entity.Member
import org.example.assignment.member.entity.MemberRole
import java.time.LocalDate
import java.time.LocalDateTime

data class SignUpResponse(
    @Schema(description = "회원 고유 번호", example = "1")
    val id: Long,

    @Schema(description = "회원 아이디", example = "cscv754")
    val loginId: String,

    @Schema(description = "회원 이름", example = "홍길동")
    val name: String,

    @Schema(description = "회원 생일", example = "1996-07-13")
    val birthDate: LocalDate,

    @Schema(description = "성별", example = "남성")
    val gender: Gender,

    @Schema(description = "회원 이메일 주소", example = "scv74502@codecraft.co.kr")
    val email: String
) {
    companion object {
        fun from(member: Member) = SignUpResponse(
            id = member.id!!,
            loginId = member.loginId,
            name = member.name!!,
            birthDate = member.birthDate,
            gender = member.gender,
            email = member.email
        )
    }
}

data class SignInResponse(
    @Schema(description = "회원 이름", example = "홍길동")
    val name: String,

    @Schema(description = "회원 유형", example = "USER")
    val memberRole: MemberRole,

    val accessToken: String,
    val refreshToken: String
)


data class MemberUpdateResponse(
    @Schema(description = "회원 정보수정 성공 여부", example = "true")
    var result: Boolean,

    @Schema(description = "회원 이름", example = "콜라곰")
    val name: String?,

    @Schema(description = "회원 이메일 주소", example = "scv74502@codecraft.co.kr")
    val email: String?
) {
    companion object {
        fun of(result: Boolean, member: Member) = MemberUpdateResponse(
            result = result,
            name = member.name,
            email = member.email
        )
    }
}

// 회원정보 조회 결과 반환하는 api
data class MemberInfoResponse(
        @Schema(description = "회원 아이디", example = "cscv754")
        val loginId: String,

        @Schema(description = "회원 이름", example = "홍길동")
        val name: String,

        @Schema(description = "회원 생일", example = "1996-07-13")
        val birthDate: LocalDate,

        @Schema(description = "성별", example = "남성")
        val gender: Gender,

        @Schema(description = "회원 이메일 주소", example = "scv74502@codecraft.co.kr")
        val email: String,

        @Schema(description = "회원의 서비스 가입일자", example = "2023-02-19")
        val createdAt: LocalDateTime
) {
        companion object {
                fun from(member: Member) = MemberInfoResponse(
                        loginId = member.loginId,
                        name = member.name!!,
                        birthDate = member.birthDate,
                        email = member.email,
                        gender = member.gender,
                        createdAt = member.createdAt
                )
        }
}

data class MemberDeleteResponse(
        @Schema(description = "회원 삭제 성공 여부", example = "true")
        val result: Boolean
)