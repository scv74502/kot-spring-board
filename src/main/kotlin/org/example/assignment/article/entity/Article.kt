package org.example.assignment.article.entity

import org.example.assignment.comment.entity.Comment
import org.example.assignment.member.entity.Member
import org.example.assignment.toSlug
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "article")
class Article (
    val title: String,
    var headline: String,
    @Lob var content: String,
    var slug: String = title.toSlug(),
    var wrotedAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member, // writer of each article

    @OneToMany(mappedBy = "article")
    val comment: MutableList<Comment> = ArrayList(), // writer of each article

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    val id: Long ?= null,

    var isDeleted: Boolean,
    var deletedDate: LocalDateTime ?= null
)