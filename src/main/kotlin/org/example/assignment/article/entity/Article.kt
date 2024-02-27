package org.example.assignment.article.entity

import org.example.assignment.article.dto.ArticleUpdateRequestDto
import org.example.assignment.article.dto.WriteRequestDto
import org.example.assignment.comment.entity.Comment
import org.example.assignment.member.entity.Member
import org.example.assignment.toSlug
import org.hibernate.annotations.ColumnDefault
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "article")
class Article (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    val id: Long ?= null,

    var title: String,

    @Lob var content: String,
    var slug: String = title.toSlug(),
    var writtenAt: LocalDateTime,

    var updatedAt: LocalDateTime?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member, // writer of each article

    @OneToMany(mappedBy = "article")
    val comment: MutableList<Comment> = ArrayList(), // writer of each article


    @ColumnDefault("FALSE")
    var isDeleted: Boolean,
    var deletedDate: LocalDateTime ?= null
) {
    companion object {
        fun from(request: WriteRequestDto, writer:Member) = Article(
            title = request.title,
            writtenAt = LocalDateTime.now(),
            content = request.content,
            isDeleted = false,
            member = writer,
            updatedAt = null
        )
    }

    fun update(request: ArticleUpdateRequestDto){
        this.content = request.content
        this.title = request.title
        this.updatedAt = LocalDateTime.now()
    }
}