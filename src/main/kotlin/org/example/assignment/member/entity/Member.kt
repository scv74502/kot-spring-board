package org.example.assignment.member.entity

import org.example.assignment.article.entity.Article
import org.example.assignment.comment.entity.Comment
import org.example.assignment.common.status.Gender
import org.example.assignment.member.dto.UpdateRequest
import org.example.assignment.member.dto.SignUpRequest
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "member")
class Member (
    @Column(nullable = false, length = 30, updatable = false, unique = true)
    val loginId: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false, length = 100)
    var name: String? = null,

    val createdAt: LocalDateTime,

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val birthDate: LocalDate,

    @Column(nullable = false, length=6)
    @Enumerated(EnumType.STRING)
    var gender: Gender,

    @Column(nullable = false, length = 50)
    var email: String,

    @Enumerated(EnumType.STRING)
    var role: MemberRole = MemberRole.USER,

    @OneToMany(mappedBy = "member")
    val wroteArticles: MutableList<Article> = ArrayList<Article>(), // 사용자가 작성한 게시글 모음

    @OneToMany(mappedBy = "member")
    val wroteComments: MutableList<Comment> = ArrayList<Comment>()  // 사용자가 작성한 댓글들 모음

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    val id: Long ?= null

    companion object {
        fun from(request: SignUpRequest, encoder: PasswordEncoder) = Member(
                loginId = request.loginId,
                password = encoder.encode(request.password),
                name = request.name,
                birthDate = request.birthDate,
                gender = request.gender,
                email = request.email,
                createdAt = LocalDateTime.now()
//                wroteArticles = ArrayList()
        )
    }
    fun update(newMember: UpdateRequest, encoder: PasswordEncoder) {
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