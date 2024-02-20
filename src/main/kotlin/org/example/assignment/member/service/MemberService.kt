package org.example.assignment.member.service

import org.example.assignment.member.dto.MemberSignUpRequestDto
import org.example.assignment.member.entity.Member
import org.example.assignment.member.repository.MemberRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Transactional
@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    // sign-up
    fun signUp(memberSignUpRequestDto: MemberSignUpRequestDto): HttpStatus{
        // id duplicate check
        var member: Member? = memberRepository.findByLoginId(memberSignUpRequestDto.loginId)
        if(member != null){
            return HttpStatus.BAD_REQUEST
        }

        member = Member(
            null,
            memberSignUpRequestDto.loginId,
            memberSignUpRequestDto.password,
            memberSignUpRequestDto.name,
            memberSignUpRequestDto.birthDate,
            memberSignUpRequestDto.gender,
            memberSignUpRequestDto.email
        )

        memberRepository.save(member)
        return HttpStatus.OK
    }
}

