package org.example.assignment.member.service

import org.example.assignment.member.dto.SignUpRequest
import org.example.assignment.member.dto.SignUpResponse
import org.example.assignment.member.repository.MemberRepository
import org.example.assignment.security.TokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignService (
    private val memberRepository: MemberRepository,
    private val tokenProvider: TokenProvider,
    private val encode: PasswordEncoder
) {
    @Transactional
    fun registMember(request: SignUpRequest) = SignUpResponse.from(
        memberRepository.flushOrThrow
    )

}