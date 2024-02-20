package org.example.assignment.member.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.example.assignment.common.annotation.ValidEnum
import org.example.assignment.common.status.Gender
import org.example.assignment.member.entity.Member
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class MemberSignUpRequestDto(
    val id: Long?,

    @field:NotBlank
    @JsonProperty("loginId")
    private val _loginId: String?,

    @field:NotBlank
    @field:Pattern(
        regexp="^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*])[a-zA-Z0-9!@#\$%^&*]{8,20}\$",
        message = "영문, 숫자, 특수문자를 포함한 8~20자리로 입력해주세요"
    )
    @JsonProperty("password")
    private val _password: String?,

    @field:NotBlank
    @JsonProperty("name")
    private val _name: String?,

    @field:NotBlank
    @field:Pattern(
        regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$",
        message = "날짜형식(YYYY-MM-DD)을 확인해주세요")
    private val _birthDate: String?,

    @field:NotBlank
    @field:ValidEnum(enumClass = Gender::class,
        message = "MAN 이나 WOMAN 중 하나를 선택해주세요")
    @JsonProperty("gender")
    private val _gender: String?,

    @field:NotBlank
    @field:Email
    @JsonProperty("email")
    private val _email: String?
) {
    val loginId: String
        get() = _loginId!!

    val password: String
        get() = _password!!

    val name: String
        get() = _name!!

    val birthDate: LocalDate
        get() = _birthDate!!.toLocalDate()

    val gender: Gender
        get() = Gender.valueOf(_gender!!)

    val email: String
        get() = _email!!

    private fun String.toLocalDate(): LocalDate =
        LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd"))


    fun toEntity(): Member =
        Member(id, loginId, password, name, birthDate, gender, email)
}
