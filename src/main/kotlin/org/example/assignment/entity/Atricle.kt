package org.example.assignment.entity

import org.example.assignment.toSlug
import java.time.LocalDateTime
import java.util.Locale
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Atricle (
    @Id @GeneratedValue var id: Long ?= null,
    @ManyToOne var author: User,
    var title: String,
    var content: String,
    var wrotedAt: LocalDateTime = LocalDateTime.now(),
    var slug: String = title.toSlug()
)