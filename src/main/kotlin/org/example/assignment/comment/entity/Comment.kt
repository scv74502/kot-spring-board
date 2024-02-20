package org.example.assignment.comment.entity

import org.example.assignment.member.entity.Member
import java.time.LocalDateTime
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

class Comment (
    @Id @GeneratedValue var id:Long ?= null,
    var content: String,
    var like: Int ?= 0,
    var wrotedAt: LocalDateTime = LocalDateTime.now(),
    @ManyToOne var authorId: Member,
    @ManyToOne var articleId: Long,
    var parentId: Long
)