package org.example.assignment.member.entity

import org.example.assignment.common.status.Gender
import org.example.assignment.member.dto.MemberUpdateRequest
import org.example.assignment.member.dto.SignUpRequest
import org.hibernate.annotations.CreationTimestamp
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    val id: Long ?= null,

    @Column(nullable = false, length = 30, updatable = false, name = "login_id", unique = true)
    val loginId: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false, length = 100)
    var name: String? = null,

    @Column(nullable = false)
    val birthDate: LocalDate,

    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false, length=6)
    @Enumerated(EnumType.STRING)
    var gender: Gender,

    @Column(nullable = false, length = 50)
    var email: String,

    @Enumerated(EnumType.STRING)
    var role: MemberRole = MemberRole.USER

    ) {
    companion object {
        fun from(request: SignUpRequest) = Member(
                loginId = request.loginId,
                password = request.password,
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