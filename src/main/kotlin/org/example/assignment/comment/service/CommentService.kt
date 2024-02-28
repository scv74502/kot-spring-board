package org.example.assignment.comment.service

import com.querydsl.jpa.impl.JPAQueryFactory
import org.example.assignment.article.entity.Article
import org.example.assignment.article.repository.ArticleRepository
import org.example.assignment.comment.dto.CommentWriteRequestDto
import org.example.assignment.comment.dto.CommentResponseDto
import org.example.assignment.comment.entity.Comment
import org.example.assignment.comment.repository.CommentRepository
import org.example.assignment.member.entity.Member
import org.example.assignment.member.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService (
    private val memberRepository: MemberRepository,
    private val articleRepository: ArticleRepository,
    private val commentRepository: CommentRepository
//    private val queryFactory: JPAQueryFactory
) {
    @Transactional
    fun insertComment(boardId: Long, request: CommentWriteRequestDto): CommentResponseDto {
        val article: Article = articleRepository.findByIdOrNull(boardId) ?: throw IllegalArgumentException("잘못된 게시글 번호 : ${boardId}")
        val writer: Member = memberRepository.findByIdOrNull(article.member.id?:0) ?: throw IllegalArgumentException("잘못된 사용자 번호 ${article.member.id}")
        var parent:Comment? = null
        if(request.parentCommentId != null){
            parent = commentRepository.findByIdOrNull(request.parentCommentId) ?: throw IllegalArgumentException("잘못된 댓글 번호 : ${request.parentCommentId}")
        }
        val comment:Comment = Comment.from(request, writer, article, parent)
        commentRepository.save(comment)
        return CommentResponseDto.fromEntity(comment)
    }

    fun deleteComment(id: Long) {
        val comment: Comment = commentRepository.findByIdOrNull(id)?:throw IllegalArgumentException("잘못된 댓글 번호 : ${id}")
        val comments: MutableList<Comment> = commentRepository.findAllById(comment.parent?.id ?: 0)

        comments.sortWith( kotlin.Comparator<Comment> { o1, o2 ->
            (o1.parent!!.id?:0.minus(o2.parent!!.id?:0)).toInt()

        }.thenBy {
            it.writtenAt
        })

//        comments.forEach((c) -> {c
    }
}