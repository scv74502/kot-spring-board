package org.example.assignment.common.authority

//import io.jsonwebtoken.JwtBuilder
//import io.jsonwebtoken.Jwts
//import io.jsonwebtoken.io.Decoders
//import io.jsonwebtoken.security.Keys
//import lombok.extern.slf4j.Slf4j
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.stereotype.Component
//
//const val EXPIRATION_MILLISECONDS: Long = 1000 * 60 * 60
//
//@Component
//@Slf4j
//class JwtTokenProvider (
//
//) {
//    @Value("\${jwt.salt}")
//    lateinit var secretKey: String
//
//    private val key by lazy {
//        Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
//    }
//    public fun createAuth(loginId: String){
//        return create(loginId, "authToken", expMin)
//    }
//
//    private fun create(loginId: String, subject: String, expmin: Long){
//        val builder: JwtBuilder ?= Jwts.builder()
//        builder.setSubject()
//        return null
//    }
//}