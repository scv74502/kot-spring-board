package org.example.assignment.member.entity

import org.example.assignment.common.status.Gender
import org.example.assignment.member.dto.MemberUpdateRequest
import org.example.assignment.member.dto.SignUpRequest
import org.hibernate.annotations.CreationTimestamp
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Member (
    @Column(nullable = false, length = 30, updatable = false, unique = true)
    val loginId: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false, length = 100)
    var name: String? = null,

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val birthDate: LocalDate,

    @Column(nullable = false, length=6)
    @Enumerated(EnumType.STRING)
    var gender: Gender,

    @Column(nullable = false, length = 50)
    var email: String,

    @Enumerated(EnumType.STRING)
    var role: MemberRole = MemberRole.USER

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long ?= null

    val createdAt: LocalDateTime = LocalDateTime.now()
    companion object {
        fun from(request: SignUpRequest, encoder: PasswordEncoder) = Member(
                loginId = request.loginId,
                password = encoder.encode(request.password),
                name = request.name,
                birthDate = request.birthDate,
                gender = request.gender,
                email = request.email
        )
    }
    fun update(newMember: MemberUpdateRequest, encoder: PasswordEncoder) {
        this.password = newMember.newPassword
            .takeIf { it.isNotBlank() }
            ?.let { encoder.encode(it) }
            ?: this.password
        this.name = newMember.name
        this.email = newMember.email
    }
}

enum class MemberRole {
    ADMIN, USER
}