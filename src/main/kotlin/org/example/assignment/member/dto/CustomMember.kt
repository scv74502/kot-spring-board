package org.example.assignment.member.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class CustomMember (
    val id: Long,
    loginId: String,
    name: String,
    password: String,
    authorities: Collection<GrantedAuthority>
): User(loginId, password, authorities)