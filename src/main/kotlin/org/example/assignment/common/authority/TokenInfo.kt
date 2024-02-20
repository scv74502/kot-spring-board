package org.example.assignment.common.authority

data class TokenInfo(
    val grantType: String,
    val accessToken: String,
    val refreshTokenInfo: String
)
