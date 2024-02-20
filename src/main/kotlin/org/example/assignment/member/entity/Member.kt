package org.example.assignment.member.entity

import org.example.assignment.common.status.Gender
import java.time.LocalDate
import java.util.Date
import javax.persistence.*

@Entity

@Table(
    uniqueConstraints = [
        UniqueConstraint(name = "uk_member_login_id", columnNames = ["loginId"])
    ]
)
class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long ?= null,

    @Column(nullable = false, length = 30, updatable = false)
    val loginId: String,

    @Column(nullable = false, length = 100)
    val password: String,

    @Column(nullable = false, length = 100)
    val name: String,

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    val birthDate: Date,

    @Column(nullable = false, length=6)
    @Enumerated(EnumType.STRING)
    val gender: Gender,

    @Column(nullable = false, length = 50)
    val email: String

)