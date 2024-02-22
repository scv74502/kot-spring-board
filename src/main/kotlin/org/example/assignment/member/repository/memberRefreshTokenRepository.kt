package org.example.assignment.member.repository

import org.example.assignment.member.entity.MemberRefreshToken
import org.springframework.data.jpa.repository.JpaRepository

interface memberRefreshTokenRepository: JpaRepository<MemberRefreshToken, Long> {
    fun findByMemberIdAndReissueCountLessThan(id: Long, count: Long): MemberRefreshToken?
}