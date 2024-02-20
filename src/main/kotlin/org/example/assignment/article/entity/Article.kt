package org.example.assignment.article.entity

import org.example.assignment.member.entity.Member
import org.example.assignment.toSlug
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Article (
    var title: String,
    var headline: String,
    var content: String,
    @ManyToOne var author: Member,
    var slug: String = title.toSlug(),
    var wrotedAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue var id: Long ?= null
)