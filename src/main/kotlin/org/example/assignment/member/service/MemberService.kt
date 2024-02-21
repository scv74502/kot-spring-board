package org.example.assignment.member.service

import org.example.assignment.common.exception.InvalidInputException
import org.example.assignment.member.dto.MemberSignUpRequestDto
import org.example.assignment.member.entity.Member
import org.example.assignment.member.entity.MemberRole
import org.example.assignment.member.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.transaction.Transactional

@Transactional
@Service
class MemberService(
    @Autowired
    private val memberRepository: MemberRepository,

    @Autowired
    private val passwordEncoder: PasswordEncoder
)
//    : UserDetailsService
{
    // sign-up
    fun signUp(memberSignUpRequestDto: MemberSignUpRequestDto): String{
        // id duplicate check
        var member: Member? = memberRepository.findByLoginId(memberSignUpRequestDto.loginId)
        if(member != null){
            throw InvalidInputException("loginId", "이미 등록된 Id 입니다.")
        }

        member = Member(
            null,
            memberSignUpRequestDto.loginId,
            memberSignUpRequestDto.password,
            memberSignUpRequestDto.name,
            memberSignUpRequestDto.birthDate,
            LocalDateTime.now(),
            memberSignUpRequestDto.gender,
            memberSignUpRequestDto.email,
            MemberRole.USER
        )
        member.password = passwordEncoder.encode(member.password)

        memberRepository.save(member)
        return "${member.loginId} id의 희원가입이 성공했습니다!"
    }

//    override fun loadUserByUsername(username: String): UserDetails {
//        return memberRepository.findByLoginId(username)?.getAuthorities()
//            ?: throw UsernameNotFoundException("id $username Can Not Found")
//    }
}

