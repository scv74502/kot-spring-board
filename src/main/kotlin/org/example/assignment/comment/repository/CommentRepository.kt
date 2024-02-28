package org.example.assignment.comment.repository

import org.example.assignment.comment.dto.CommentResponseDto
import org.example.assignment.comment.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository
import java.util.function.Consumer


interface CommentRepository: JpaRepository<Comment, Long> {
//    fun findByArticleId(id: Long): List<CommentResponseDto> {
//
//    }
}