package org.example.assignment.security

import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
class SecurityConfig(
        private val jwtAuthenticationFilter: JwtAuthenticationFilter,
        private val entryPoint: AuthenticationEntryPoint
) : WebSecurityConfigurerAdapter() {
    private val allowedUris = arrayOf("/sign-up", "/sign-in")

//    @Bean
//    fun filterChain(http: HttpSecurity) = http
//            .csrf().disable()
//            .headers { it.frameOptions().sameOrigin() }
//            .authorizeRequests {
//                it.antMatchers(*allowedUris).permitAll()
//                        .anyRequest().authenticated()
//            }
//            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
//            .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter::class.java)
//            .exceptionHandling { it.authenticationEntryPoint(entryPoint) }
//            .build()!!

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()



    override fun configure(http: HttpSecurity) {
        http.
            httpBasic().disable()
                .csrf().disable()
                .headers { it.frameOptions().sameOrigin() }
                .authorizeRequests {
                    it.antMatchers("/log-test", "/sign-in/**", "/sign-up/**", "/sign-out/**", "/swagger-ui/**", "/")
                    .permitAll() // 로그인, 회원가입, 로그아웃, 스웨거는은 누구나 접근 가능
                    .anyRequest().authenticated()
                }
                .sessionManagement{ it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}