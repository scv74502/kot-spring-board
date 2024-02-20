package org.example.assignment.member.dto

import org.example.assignment.common.status.Gender
import java.time.LocalDate
import java.util.*

data class MemberSignUpRequestDto(
    val id: Long?,
    val loginId: String,
    val password: String,
    val name: String,
    val birthDate: Date,
    val gender: Gender,
    val email: String
)
