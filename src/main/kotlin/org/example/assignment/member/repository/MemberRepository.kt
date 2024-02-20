package org.example.assignment.member.repository

import org.example.assignment.member.entity.Member
import org.springframework.data.repository.CrudRepository

interface MemberRepository : CrudRepository<Member, Long> {
    // ID duplicate check
    fun findByLoginId(login: String): Member?
}