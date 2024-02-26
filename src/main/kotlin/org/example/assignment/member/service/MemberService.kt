package org.example.assignment.member.service


import org.example.assignment.member.dto.MemberDeleteResponse
import org.example.assignment.member.dto.MemberInfoResponse
import org.example.assignment.member.dto.UpdateRequest
import org.example.assignment.member.dto.MemberUpdateResponse
import org.example.assignment.member.entity.Member
import org.example.assignment.member.repository.MemberRefreshTokenRepository
import org.example.assignment.member.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberRefreshTokenRepository: MemberRefreshTokenRepository,
    private val encoder: PasswordEncoder
) {
    @Transactional(readOnly = true)
    fun getMemberInfo(id: Long): MemberInfoResponse {
        println("------------------${id}---------------------------")
        val result: Member = memberRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("존재하지 않는 회원입니다")
        return MemberInfoResponse.from(result)
    }

    @Transactional
    fun deleteMember(id: Long): MemberDeleteResponse {
        memberRefreshTokenRepository.deleteById(id)
        if (!memberRepository.existsById(id)) return MemberDeleteResponse(false)
        memberRepository.deleteById(id)
        return MemberDeleteResponse(true)
    }

    @Transactional
    fun updateMember(id: Long, request: UpdateRequest): MemberUpdateResponse {
        val member = memberRepository.findByIdOrNull(id)
            ?.takeIf { encoder.matches(request.password, it.password) }
            ?: throw IllegalArgumentException("아이디 혹은 비밀번호가 일치하지 않습니다.")
        member.update(request, encoder)
        return MemberUpdateResponse.of(true, member)
    }
}