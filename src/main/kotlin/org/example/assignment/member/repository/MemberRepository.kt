package org.example.assignment.member.repository

import org.example.assignment.member.entity.Member
import org.springframework.data.repository.CrudRepository
import java.util.*

interface MemberRepository : CrudRepository<Member, Long> {

    // ID duplicate check
    fun findByLoginId(login: String): Member?
}