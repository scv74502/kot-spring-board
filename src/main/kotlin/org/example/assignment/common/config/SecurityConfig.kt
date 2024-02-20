package org.example.assignment.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
class SecurityConfig {
    private val allowedUrls = arrayOf("/", "/api/**")

    @Bean
    fun filterChain(http: HttpSecurity) = http
        .cors().and()
        .httpBasic().disable()
        .csrf().disable()
        .headers { it.frameOptions().sameOrigin() }
        .authorizeRequests {
            it.antMatchers("/api/**").permitAll()	// 허용할 url 목록을 배열로 분리했다
           .anyRequest().authenticated()
        }
        .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        .build()!!
}
