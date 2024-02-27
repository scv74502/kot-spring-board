package org.example.assignment.member.service

import org.example.assignment.member.dto.SignInRequest
import org.example.assignment.member.dto.SignInResponseDto
import org.example.assignment.member.dto.SignUpRequest
import org.example.assignment.member.dto.SignUpResponseDto
import org.example.assignment.member.entity.Member
import org.example.assignment.member.entity.MemberRefreshToken
import org.example.assignment.member.repository.MemberRefreshTokenRepository
import org.example.assignment.member.repository.MemberRepository
import org.example.assignment.security.TokenProvider
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

// inlife function returns Logger object
inline fun <reified T> T.logger() = LoggerFactory.getLogger(T::class.java)!!

@Service
class SignService (
    private val memberRepository: MemberRepository,
    private val memberRefreshTokenRepository: MemberRefreshTokenRepository,
    private val tokenProvider: TokenProvider,
    private val encoder: PasswordEncoder
) {
    @Transactional
    fun registerMember(request: SignUpRequest) = SignUpResponseDto.from(
        if(memberRepository.findByLoginId(request.loginId) != null){
            throw IllegalArgumentException("이미 사용중인 아이디입니다")
        } else {
            memberRepository.save(Member.from(request, encoder))
        }
    )

    @Transactional
    fun signIn(request: SignInRequest): SignInResponseDto {
//        println("--------------------input values---------------------------\n ${request.toString()}")
        val member = memberRepository.findByLoginId(request.loginId)
            ?.takeIf { encoder.matches(request.password, it.password) }
            ?: throw IllegalArgumentException("아이디 또는 비밀번호가 잘못되었습니다")
//        println("--------------------member password---------------------------\n ${member.password}")
        val accessToken = tokenProvider.createAccessToken("${member.id}:${member.role}")
        val refreshToken = tokenProvider.createRefreshToken()
        memberRefreshTokenRepository.findByIdOrNull(member.id)?.updateRefreshToken(refreshToken)
            ?: memberRefreshTokenRepository.save(MemberRefreshToken(member, refreshToken))
        return SignInResponseDto(member.name!!, member.role, accessToken, refreshToken)
    }
}