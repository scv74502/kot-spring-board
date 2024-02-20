package org.example.assignment.member.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Member (
    var login: String,
    var userName: String,
    var userId: String,
//    var password: String,
//    var userJoinDate: Date,
//    var isDeleted: Boolean,
    @Id @GeneratedValue var id:Long ?= null
)