package org.example.assignment.member.service

import org.example.assignment.common.exception.InvalidInputException
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
    fun signUp(memberSignUpRequestDto: MemberSignUpRequestDto): String{
        // id duplicate check
        var member: Member? = memberRepository.findByLoginId(memberSignUpRequestDto.loginId)
        if(member != null){
            throw InvalidInputException("loginId", "이미 등록된 Id 입니다.")
        }

//        member = Member(
//            null,
//            memberSignUpRequestDto.loginId,
//            memberSignUpRequestDto.password,
//            memberSignUpRequestDto.name,
//            memberSignUpRequestDto.birthDate,
//            memberSignUpRequestDto.gender,
//            memberSignUpRequestDto.email
//        ) 를 한 줄로 나타내면
        member = memberSignUpRequestDto.toEntity()

        memberRepository.save(member)
        return "${member.loginId} id의 희원가입이 성공했습니다!"
    }
}

