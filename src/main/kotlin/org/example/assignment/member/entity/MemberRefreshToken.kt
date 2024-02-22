package org.example.assignment.member.entity

import org.example.assignment.member.entity.Member
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.MapsId
import javax.persistence.OneToOne

@Entity
class MemberRefreshToken (
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name="member_id")
    val member: Member,
    private var refreshToken: String

    /* 일대일 매핑, 아이디 매핑 */
) {
    @Id
    val memberId: Long ?= null
    private var reissueCount = 0

    fun updateRefreshToken(refreshToken: String) {
        this.refreshToken = refreshToken
    }

    // refreshToken이 올바른 지 검증하기
    fun validRefreshToken(refreshToken: String) =
        this.refreshToken ==refreshToken

    // 재발급 요청 횟수 증가시키기
    fun addReissueCount() {
        reissueCount++
    }
}