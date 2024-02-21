package org.example.assignment.common.config

import org.example.assignment.member.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
class SecurityConfig (
    @Autowired
    private val memberService: MemberService,

    @Autowired
    private val passwordEncoder: PasswordEncoder
): WebSecurityConfigurerAdapter() {

    companion object {
        const val LOGIN_SUCCESS_URL: String = "api/login/success"
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .userDetailsService(memberService)
            .passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity?) {
        http
            ?.csrf()?.disable()
            ?.authorizeRequests{
                it.antMatchers("/", "api/**").permitAll()
            }
            ?.sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            ?.build()!!
    }
}