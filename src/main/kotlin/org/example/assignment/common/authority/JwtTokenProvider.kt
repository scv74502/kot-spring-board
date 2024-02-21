package org.example.assignment.common.authority
//
//import io.jsonwebtoken.Jwts
//import io.jsonwebtoken.io.Decoders
//import io.jsonwebtoken.security.Keys
//import io.jsonwebtoken.security.SignatureAlgorithm
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.PropertySource
//import org.springframework.security.core.Authentication
//import org.springframework.security.core.GrantedAuthority
//import org.springframework.stereotype.Component
//import org.springframework.stereotype.Service
//import java.util.*
//import javax.crypto.spec.SecretKeySpec
//
//const val EXP_HOUR: Long = 1000 * 60 * 60
//
//@PropertySource("classpath:jwt.yaml")
//@Service
//class JwtTokenProvider {
//    @Value("\${jwt.secret}")
//    lateinit var secretKey: String
//
//    private val key by lazy {
//        Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
//    }
//
//    /*
//    * Create Token
//    */
//    fun createToken(authentication: Authentication): TokenInfo {
//        val authorities: String = authentication
//            .authorities
//            .joinToString(",", transform = GrantedAuthority::getAuthority)
//
//        val now = Date()
//        val accessExpiration = Date(now.time + EXP_HOUR * 1)    // 1시간으로 만료시간 설정
//
//        // Access Token
//        val accessToken = Jwts.builder()
//            .signWith(SecretKeySpec(secretKey.toByteArray(), io.jsonwebtoken.SignatureAlgorithm.HS512.jcaName))
//            .compact()
//    }
//}