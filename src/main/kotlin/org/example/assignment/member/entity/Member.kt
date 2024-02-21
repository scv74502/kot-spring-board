package org.example.assignment.member.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.example.assignment.common.status.Gender
import org.hibernate.annotations.CreationTimestamp
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.stream.Collectors
import javax.persistence.*
import kotlin.jvm.Transient

@Entity

@Table(
    uniqueConstraints = [
        UniqueConstraint(name = "uk_member_login_id", columnNames = ["loginId"])
    ]
)
class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    val id: Long ?= null,

    @Column(nullable = false, length = 30, updatable = false, name = "login_id")
    val loginId: String,

    @Column(nullable = false, length = 100)
    var password: String,

    @Column(nullable = false, length = 100)
    val name: String,

    @Column(nullable = false)
//    @Temporal(TemporalType.DATE)
    val birthDate: LocalDate,

    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false, length=6)
    @Enumerated(EnumType.STRING)
    var gender: Gender,

    @Column(nullable = false, length = 50)
    var email: String,

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @Transient
    var roles: MutableSet<MemberRole>

//    @Enumerated(EnumType.STRING)
//    var role: MemberRole
    ) {


    fun getAuthorities(): User{
        return User(this.loginId, this.password, this.roles.stream().map {
            role -> SimpleGrantedAuthority("ROLE_$role")
        }.collect(Collectors.toSet()))
    }
}

enum class MemberRole {
    ADMIN, USER
}