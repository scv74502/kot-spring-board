package org.example.assignment.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {
    private val allowedUrls = arrayOf("/", "/swagger-ui/**", "/v3/**", "/sign-up", "/sign-in")	// sign-up, sign-in 추가


    @Override
    protected fun filterChain(http: HttpSecurity):SecurityFilterChain {
        return http
                .csrf().disable()
                .headers { it.frameOptions().sameOrigin() }
                .authorizeRequests {
                    it.antMatchers(*allowedUrls).permitAll()    // 허용할 url 목록을 배열로 분리했다
                            .anyRequest().authenticated()
                }
                .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
                .build()!!
    }

}