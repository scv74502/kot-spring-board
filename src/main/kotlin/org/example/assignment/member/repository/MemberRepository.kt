package org.example.assignment.member.repository

import org.example.assignment.member.entity.Member
import org.springframework.data.repository.CrudRepository

interface MemberRepository : CrudRepository<Member, Long> {
    fun findByLogin(login: String): Member?
}