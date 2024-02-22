package org.example.assignment.member.service


import org.example.assignment.member.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Transactional
@Service
class MemberService(
    @Autowired
    private val memberRepository: MemberRepository
)
//    : UserDetailsService
{
    // sign-up
//    fun signUp(memberSignUpRequest: MemberSignUpRequest): String{
//        // id duplicate check
//        var member: Member? = memberRepository.findByLoginId(memberSignUpRequest.loginId)
//        if(member != null){
//            throw InvalidInputException("loginId", "이미 등록된 id 입니다.")
//        }
//
//        member = Member(
//            null,
//            memberSignUpRequest.loginId,
//            memberSignUpRequest.password,
//            memberSignUpRequest.name,
//            memberSignUpRequest.birthDate,
//            LocalDateTime.now(),
//            memberSignUpRequest.gender,
//            memberSignUpRequest.email,
//            MemberRole.USER
//        )
//        member.password = passwordEncoder.encode(member.password)
//
//        memberRepository.save(member)
//        return "${member.loginId} id의 희원가입이 성공했습니다!"
//    }

//    override fun loadUserByUsername(username: String): UserDetails {
//        return memberRepository.findByLoginId(username)?.getAuthorities()
//            ?: throw UsernameNotFoundException("id $username Can Not Found")
//    }
}

