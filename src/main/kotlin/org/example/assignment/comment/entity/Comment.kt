package org.example.assignment.comment.entity

import lombok.AccessLevel
import lombok.NoArgsConstructor
import org.example.assignment.article.entity.Article
import org.example.assignment.comment.dto.CommentWriteRequestDto
import org.example.assignment.member.entity.Member
import org.hibernate.annotations.ColumnDefault
import java.time.LocalDateTime
import javax.persistence.*


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "comment")
class Comment (

    @Column(nullable = false, length = 1000)
    var content: String,

//    var liked: Int ?= 0,

    var writtenAt: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member, // 댓글 작성한 사용자 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    val article: Article,   // 해당 댓글이 작성된 게시글 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    val parent: Comment?,

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    val children: MutableList<Comment> = ArrayList(),

    @ColumnDefault("FALSE")
    var isDeleted: Boolean
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    val id:Long ?= null

    companion object {
        fun from(request:CommentWriteRequestDto, writer:Member, article: Article, parent: Comment?) = Comment(
            content = request.content,
            writtenAt = LocalDateTime.now(),
            article = article,
            member = writer,
            parent = parent,
            isDeleted = false
        )
    }
}