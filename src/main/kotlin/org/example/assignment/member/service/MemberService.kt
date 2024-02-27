package org.example.assignment.member.service


import org.example.assignment.member.dto.MemberDeleteResponseDto
import org.example.assignment.member.dto.MemberInfoResponseDto
import org.example.assignment.member.dto.MemberUpdateRequest
import org.example.assignment.member.dto.MemberUpdateResponseDto
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
    fun getMemberInfo(id: Long): MemberInfoResponseDto {
//        println("------------------${id}---------------------------")
        val result: Member = memberRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("존재하지 않는 회원입니다")
        return MemberInfoResponseDto.from(result)
    }

    @Transactional
    fun deleteMember(id: Long): MemberDeleteResponseDto {
        memberRefreshTokenRepository.deleteById(id)
        val member: Member = memberRepository.findByIdOrNull(id) ?: return MemberDeleteResponseDto(false)
        member.isDeleted = true
        memberRepository.save(member)
        return MemberDeleteResponseDto(true)
    }

    @Transactional
    fun updateMember(id: Long, request: MemberUpdateRequest): MemberUpdateResponseDto {
        val member = memberRepository.findByIdOrNull(id)
            ?.takeIf { encoder.matches(request.password, it.password) }
            ?: throw IllegalArgumentException("아이디 혹은 비밀번호가 일치하지 않습니다.")
        member.update(request, encoder)
        return MemberUpdateResponseDto.of(true, member)
    }
}