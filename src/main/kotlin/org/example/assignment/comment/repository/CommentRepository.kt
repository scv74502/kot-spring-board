package org.example.assignment.comment.repository

import org.example.assignment.comment.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository


interface CommentRepository: JpaRepository<Comment, Long> {
}