package org.example.assignment.common.authority

import org.example.assignment.common.authority.entity.MemberRefreshToken
import org.springframework.data.jpa.repository.JpaRepository

interface memberRefreshTokenRepository: JpaRepository<MemberRefreshToken, Long> {
    fun findByMemberIdAndReissueCountLessThan(id: Long, count: Long): MemberRefreshToken?
}