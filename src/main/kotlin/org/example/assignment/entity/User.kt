package org.example.assignment.entity

import java.util.Date
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User (
    var login: String,
    var userName: String,
    var userId: String,
//    var password: String,
//    var userJoinDate: Date,
//    var isDeleted: Boolean,
    @Id @GeneratedValue var id:Long ?= null
)