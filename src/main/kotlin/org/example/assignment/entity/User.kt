package org.example.assignment.entity

import java.util.Date
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User (
    @Id @GeneratedValue var id:Long ?= null,
    var userId: String,
    var password: String,
    var userName: String,
    var userJoinDate: Date,
    var isDeleted: Boolean
)