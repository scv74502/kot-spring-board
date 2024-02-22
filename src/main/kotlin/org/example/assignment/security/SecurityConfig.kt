package org.example.assignment.security

import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@EnableWebSecurity
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
                .sessionManagement().sessionCreationPolicy((SessionCreationPolicy.STATELESS))
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .antMatchers("/register/**", "/login/**", "/logout/**", "/swagger-ui/**").permitAll() // 로그인, 회원가입은 누구나 접근 가능
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}